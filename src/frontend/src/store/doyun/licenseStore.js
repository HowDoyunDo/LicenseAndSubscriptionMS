
export default  {    
    namespaced: true,
    state: {
        selectedlicense: '',
    },
    mutations: {
        SELECT_LIC(state, selectedlicense) {
            state.selectedlicense = selectedlicense;
        }
    },
    actions: {

    },
    getters: {

    }
}