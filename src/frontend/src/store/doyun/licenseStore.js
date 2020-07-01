
export default  {    
    namespaced: true,
    state: {
        selectedlicense: '',
        generalusers: ''
    },
    mutations: {
        SELECT_LIC(state, selectedlicense) {
            state.selectedlicense = selectedlicense;
        },
        SELECT_USR(state, generalusers) {
            state.generalusers = generalusers;
        }
    },
    actions: {

    },
    getters: {

    }
}