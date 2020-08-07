
export default  {    
    namespaced: true,
    state: {
        categorylist: '',
        productlist: '',
        productadd: '',
        subscribeAlllist: '',
        subscribeAdd: '',
        promotion: '',
        promotionAdd: '',
        orderlist: '',
        subscriptioncancelchange: '',
        userlist: '',
        analysisSales: '',
        analysisProduct: '',
        licenseAlllist: '',
        batch: '',
        home: '',
        licenseList: '',
        myinfo: '',
        pricequestion: '',
        pricequestionlist: '',
    },
    mutations: {
        SELECT_LIC(state, selectedlicense) {
            state.selectedlicense = selectedlicense;
        },
        SELECT_USR(state, generalusers) {
            state.generalusers = generalusers;
        },
        SELECT_AOL(state, data) {
            state.alertOverLicense = data;
        },
        UPDATE_POL(state, data) {
            state.updatePolicy = data;
        },
        UPDATE_UED(state, data) {
            state.updateEndDate = data;
        },
        UPDATE_LCN(state, data) {
            state.lcno = data;
        },
        CLR_MENU(state) {
            state.categorylist= ''
            state.productlist= ''
            state.productadd= ''
            state.subscribeAlllist= ''
            state.subscribeAdd= ''
            state.promotion= ''
            state.promotionAdd= ''
            state.orderlist= ''
            state.subscriptioncancelchange= ''
            state.userlist= ''
            state.analysisSales= ''
            state.analysisProduct= ''
            state.licenseAlllist= ''
            state.batch= ''
            state.home= ''
            state.licenseList= ''
            state.myinfo= ''
            state.pricequestion= ''
            state.pricequestionlist= ''
        },
        SEL_MENU(state, value) {
            state.categorylist= ''
            state.productlist= ''
            state.productadd= ''
            state.subscribeAlllist= ''
            state.subscribeAdd= ''
            state.promotion= ''
            state.promotionAdd= ''
            state.orderlist= ''
            state.subscriptioncancelchange= ''
            state.userlist= ''
            state.analysisSales= ''
            state.analysisProduct= ''
            state.licenseAlllist= ''
            state.batch= ''
            state.home= ''
            state.licenseList= ''
            state.myinfo= ''
            state.pricequestion= ''
            state.pricequestionlist= ''
            switch(value) {
                case 1: state.categorylist = 'selected';
                  break;
                case 2: state.productlist = 'selected';
                  break; 
                case 3: state.productadd = 'selected';
                  break;
                case 4: state.subscribeAlllist = 'selected';
                  break;
                case 5: state.subscribeAdd = 'selected';
                  break;
                case 6: state.promotion = 'selected';
                  break;
                case 7: state.promotionAdd = 'selected';
                  break;
                case 8: state.orderlist = 'selected';
                  break;
                case 9: state.subscriptioncancelchange = 'selected';
                  break;
                case 10: state.userlist = 'selected';
                  break;
                case 11: state.analysisSales = 'selected';
                  break;
                case 12: state.analysisProduct = 'selected';
                  break;
                case 13: state.licenseAlllist = 'selected';
                  break;
                case 14: state.batch = 'selected';
                  break;
                case 15: state.home = 'selected';
                  break;
                case 16: state.licenseList = 'selected';
                  break;
                case 17: state.myinfo = 'selected';
                  break;
                case 18: state.pricequestion = 'selected';
                  break;
                case 19: state.pricequestionlist = 'selected';
                  break;
            }
        } 
    },
    actions: {

    },
    getters: {

    }
}