
export default  {    
    namespaced: true,
    state: {
        solution: '',
        product: '',

        iworks: '',
        ixeb: '',
        igate: '',
        apim: '',
        ecross: '',
        xtorm: '',
        experdb: '',
        libeka: '',
        libekas4: '',
        myguard: '',
        centdoc: '',

        uPolicies: [],
    },
    mutations: {
        CHANGE_SEL(state, sol) {
            return state.solution = sol;
        },
        CHANGE_PDT(state, pdt) {
            return state.product = pdt;
        },
        ACTIVE_TAB(state, pdt) {
            state.iworks = ''; state.ixeb = ''; state.igate = ''; state.apim = ''; state.ecross = '';
            state.xtorm = ''; state.experdb = ''; state.libeka = ''; state.libekas4 = ''; state.myguard = ''; state.centdoc = '';
            switch(pdt) {
                case 'iworks': state.iworks = 'active'; break;
                case 'ixeb': state.ixeb = 'active'; break;
                case 'igate': state.igate = 'active'; break;
                case 'apim': state.apim = 'active'; break;
                case 'ecross': state.ecross = 'active'; break;
                case 'xtorm': state.xtorm = 'active'; break;
                case 'experdb': state.experdb = 'active'; break;
                case 'libeka': state.libeka = 'active'; break;
                case 'libekas4': state.libekas4 = 'active'; break;
                case 'myguard': state.myguard = 'active'; break;
                case 'centdoc': state.centdoc = 'active'; break;
            }
        },

        CHANGE_UPLCY(state, data) {
            return state.uPolicies = data;
        }
    },
    actions: {

    },
    getters: {

    }
}