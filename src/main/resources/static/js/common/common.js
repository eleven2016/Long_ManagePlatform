var headerVue;
var menuVue;
$(document).ready(function () {
    /**
     * 头部vue
     * @type {*|Vue}
     */
    headerVue =new Vue({
        el:"#header",
        data:{
            headerModel:{

            }
        },
        ready:function(){

        },
        methods:{

        }
    });
    /**
     * 菜单VUE
     * @type {*|Vue}
     */
     menuVue =new Vue({
        el:"#menu",
        data:{
            menuModel:{
                menus:[]
            }
        },
        ready:function(){
            this.loadMenus();
        },
        methods:{
            loadMenus:function(){
                menuVue.menuModel.menus=[{
                    id:"1",
                    code:"basicSetting",
                    name:"基础配置",
                    url:"",
                    jsUrl:"",
                    level:"1",
                    subMenus:[{
                        id:"2",
                        code:"userSetting",
                        name:"用户配置",
                        url:"",
                        jsUrl:"",
                        level:"2",
                    },
                        {
                            id:"3",
                            code:"menuSetting",
                            name:"菜单配置",
                            url:"",
                            jsUrl:"",
                            level:"2",
                        },
                        {
                            id:"4",
                            code:"roleSetting",
                            name:"角色配置",
                            url:"",
                            jsUrl:"",
                            level:"2",
                        }
                    ]
                }]
            }
        }
    });
    menuVue.loadMenus();
});

