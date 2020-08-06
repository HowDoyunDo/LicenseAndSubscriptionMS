
export default  {    
    namespaced: true,
    state: {
        selectedlicense: '',
        generalusers: '',
        alertOverLicense: [],
        updatePolicy: '',
        updateEndDate: '',
        lcno: '',
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
    },
    actions: {

    },
    getters: {

    }
}