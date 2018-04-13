Ext.onReady(function () {
    Ext.define('TechZoo.model.Country', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id', type: 'int'},
            {name: 'country_name', type: 'string'}

        ]
    });

    Ext.define('TechZoo.store.CountryStore', {
        extend: 'Ext.data.Store',
        storeId: 'countryStore',
        model: 'TechZoo.model.Country',
        fields: ['id', 'country_name'],
        proxy: {
            type: 'ajax',
            url: '/petshop/load',
            reader: {
                type: 'json',
                root: 'books'
            }
        },
        autoLoad: true
    });

    Ext.define('TechZoo.view.Countrylist', {
        extend: 'Ext.grid.Panel',
        alias: 'widget.countrylist',
        title: 'Список стран',
        store: 'CountryStore',
        initComponent: function () {
            this.tbar = [{
                    text: 'Добавить страну',
                    action: 'add',
                    iconCls: 'book-add'
                }];
            this.columns = [
                {header: 'id', dataIndex: 'id',width: 30 },
                {header: 'Название страны', dataIndex: 'country_name',width: 250},
                {header: 'Действие', width: 100,
                    renderer: function (v, m, r) {
                        var id = Ext.id();
                        Ext.defer(function () {
                            Ext.widget('image', {
                                renderTo: id,
                                name: 'delete',
                                src: 'resources/images/book_delete.png',
                                listeners: {
                                    afterrender: function (me) {
                                        me.getEl().on('click', function () {
                                            var grid = Ext.ComponentQuery.query('countrylist')[0];
                                            if (grid) {
                                                var sm = grid.getSelectionModel();
                                                var rs = sm.getSelection();
                                                if (!rs.length) {
                                                    Ext.Msg.alert('Внимание', 'Не выбрана страна');
                                                    return;
                                                }
                                                Ext.Msg.confirm('Удалить страну',
                                                        'Вы действительно это хотите сделать?',
                                                        function (button) {
                                                            if (button == 'yes') {
                                                                //grid.store.remove(rs[0]);
                                                                var book = rs[0].getData();
                                                                Ext.Ajax.request({
                                                                    url: '/petshop/deletecountry',
                                                                    method: 'POST',
                                                                    jsonData: book,
                                                                    success: function (response) {
                                                                        var grid = Ext.ComponentQuery.query('countrylist')[0];
                                                                        grid.getStore().load();
                                                                    }
                                                                });
                                                            }
                                                        });
                                            }
                                        });
                                    }
                                }
                            });
                        }, 50);
                        return Ext.String.format('<div id="{0}"></div>', id);
                    }
                }
            ];
            this.callParent(arguments);
        }
    });

    Ext.define('TechZoo.view.Countryform', {
        extend: 'Ext.window.Window',
        alias: 'widget.countryform',
        title: 'Add Book',
        width: 350,
        layout: 'fit',
        resizable: false,
        closeAction: 'hide',
        modal: true,
        config: {
            recordIndex: 0,
            action: ''
        },
        items: [{
                xtype: 'form',
                layout: 'anchor',
                bodyStyle: {
                    background: 'none',
                    padding: '10px',
                    border: '0'
                },
                defaults: {
                    xtype: 'textfield',
                    anchor: '100%'
                },
                items: [{
                        xtype:'hidden',
                        name: 'id',
                        fieldLabel: 'ИД'
                    },
                    {
                        name: 'country_name',
                        fieldLabel: 'Название страны'
                    }]
            }],
        buttons: [{
                text: 'OK',
                action: 'add'
            }, {
                text: 'Сбросить',
                handler: function () {
                    this.up('window').down('form').getForm().reset();
                }
            }, {
                text: 'Отмена',
                handler: function () {
                    this.up('window').close();
                }
            }]
    });

    Ext.define('TechZoo.controller.CountriesController', {
        extend: 'Ext.app.Controller',
        stores: ['CountryStore'],
        views: ['Countrylist', 'Countryform'],
        refs: [{
                ref: 'formWindow',
                xtype: 'countryform',
                selector: 'countryform',
                autoCreate: true
            }],
        init: function () {
            this.control({
                'countrylist > toolbar > button[action=add]': {
                    click: this.showAddForm
                },
                'countrylist': {
                    itemdblclick: this.onRowdblclick
                },
                'countryform button[action=add]': {
                    click: this.doAddBook
                }
            });
        },
        onRowdblclick: function (me, record, item, index) {
            var win = this.getFormWindow();
            win.setTitle('Редактировать информацию о стране');
            win.setAction('edit');
            win.setRecordIndex(index);
            win.down('form').getForm().setValues(record.getData());
            win.show();
        },
        showAddForm: function () {
            var win = this.getFormWindow();
            win.setTitle('Добавить страну');
            console.log('Add country');
            win.setAction('add');
            win.down('form').getForm().reset();
            win.show();
        },
        doAddBook: function () {
            var win = this.getFormWindow();

            var store = Ext.ComponentQuery.query('countrylist')[0];
            console.log('I here');
            var values = win.down('form').getValues();
            console.log(values);

            var action = win.getAction();
            //var book = Ext.create('TechZoo.model.Book', values);
            var url = '';
            if (action == 'edit') {
                url = '/petshop/updatecountry';
            } else {
                url = '/petshop/savecountry';
            }
            Ext.Ajax.request({
                url: url,
                method: 'POST',
                jsonData: values,
                success: function (response) {
                    store.getStore().load()
                    //store.load();
                }
            });
            win.close();
        }
    });

    Ext.application({
        name: 'TechZoo',
        controllers: ['CountriesController'],
        launch: function () {
            Ext.widget('countrylist', {
                width: 500,
                height: 300,
                renderTo: 'output'
            });
        }
    }
    );
});