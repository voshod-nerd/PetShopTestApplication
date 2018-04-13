Ext.onReady(function () {
    Ext.define('TechZoo.model.City', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id', type: 'int'},
            {name: 'city_name', type: 'string'},
            {name: 'idcountry', type: 'int'},
            {name: 'country', type: 'auto', mapping: 'country.country_name'}
        ]
    });
    
    
    
    Ext.define('TechZoo.model.Country', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id', type: 'int'},
            {name: 'country_name', type: 'string'}

        ]
    });
    
    
    var counrystore = Ext.create('Ext.data.Store', {
        model: 'TechZoo.model.Country',
        proxy: {
            type: 'ajax',
            url: '/petshop/load',
            reader: {
                type: 'json',
                root: 'books'
            }
        }
    });
    
    
 
    

    Ext.define('TechZoo.store.CityStore', {
        extend: 'Ext.data.Store',
        storeId: 'countryStore',
        model: 'TechZoo.model.City',
        fields: [
            {name: 'id', type: 'int'},
            {name: 'city_name', type: 'string'},
            {name: 'idcountry', type: 'int'},
            {name: 'country', type: 'string', mapping: 'country.country_name'}
        ],
       
        proxy: {
            type: 'ajax',
            url: '/petshop/loadcity',
            reader: {
                type: 'json',
                root: 'books'
            }
        },
        autoLoad: true
    });

   


    Ext.define('TechZoo.view.Citylist', {
        extend: 'Ext.grid.Panel',
        alias: 'widget.citylist',
        title: 'Список стран',
        store: 'CityStore',
        initComponent: function () {
            this.tbar = [{
                    text: 'Добавить страну',
                    action: 'add',
                    iconCls: 'book-add'
                }];
            this.columns = [
                {header: 'id', dataIndex: 'id', width: 30},
                {header: 'Название города', dataIndex: 'city_name', width: 150},
                {header: 'Id страны', dataIndex: 'idcountry', width: 150},
                {header: 'Название страны ', dataIndex: 'country', width: 150},
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
                                            var grid = Ext.ComponentQuery.query('citylist')[0];
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
                                                                console.log(book);
                                                                book.country=null;
                                                                Ext.Ajax.request({
                                                                    url: '/petshop/deletecity',
                                                                    method: 'POST',
                                                                    jsonData: book,
                                                                    success: function (response) {
                                                                        var grid = Ext.ComponentQuery.query('citylist')[0];
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

    Ext.define('TechZoo.view.Cityform', {
        extend: 'Ext.window.Window',
        alias: 'widget.cityform',
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
                        name: 'city_name',
                        fieldLabel: 'Название города'
                    },
                     
                    {  
                        xtype: 'combo',
                        store: counrystore,
                        mode: 'local',
                        name: 'idcountry',
                        fieldLabel: 'Cтрана',
                        valueField: 'id',
                        displayField: 'country_name',
         
                    }
                    
                ]
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

    Ext.define('TechZoo.controller.CityController', {
        extend: 'Ext.app.Controller',
        stores: ['CityStore'],
        views: ['Citylist', 'Cityform'],
        refs: [{
                ref: 'formWindow',
                xtype: 'cityform',
                selector: 'cityform',
                autoCreate: true
            }],
        init: function () {
            this.control({
                'citylist > toolbar > button[action=add]': {
                    click: this.showAddForm
                },
                'citylist': {
                    itemdblclick: this.onRowdblclick
                },
                'cityform button[action=add]': {
                    click: this.doAddBook
                }
            });
        },
        onRowdblclick: function (me, record, item, index) {
            var win = this.getFormWindow();
            win.setTitle('Редактировать информацию о городе');
            win.setAction('edit');
            win.setRecordIndex(index);
            win.down('form').getForm().setValues(record.getData());
            win.show();
        },
        showAddForm: function () {
            var win = this.getFormWindow();
            win.setTitle('Добавить город');
            console.log('Add country');
            win.setAction('add');
            win.down('form').getForm().reset();
            win.show();
        },
        doAddBook: function () {
            var win = this.getFormWindow();

            var store = Ext.ComponentQuery.query('citylist')[0];
            console.log('I here');
            var values = win.down('form').getValues();
            console.log(values);

            var action = win.getAction();
            //var book = Ext.create('TechZoo.model.Book', values);
            var url = '';
            if (action == 'edit') {
                url = '/petshop/updatecity';
            } else {
                url = '/petshop/savecity';
            }
            Ext.Ajax.request({
                url: url,
                method: 'POST',
                jsonData: values,
                success: function (response) {
                    store.getStore().load();

                }
            });
            win.close();
        }
    });

    Ext.application({
        name: 'TechZoo',
        controllers: ['CityController'],
        launch: function () {
            Ext.widget('citylist', {
                width: 650,
                height: 400,
                renderTo: 'output'
            });
        }
    }
    );
});