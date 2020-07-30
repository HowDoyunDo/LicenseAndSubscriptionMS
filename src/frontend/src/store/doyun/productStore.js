
export default  {    
    namespaced: true,
    state: {
        // 제품 객체
        product: '',

        // 제품 탭
        tabs: '',

        // 선택 솔루션에 대한 정책정보 저장
        uPolicies: [],
        aPolicies: [],
        // polno: '',

        // 구독 신청 시 구독 시작일
        startdate: '',
        enddate: '',

        // 프로모션 정보 저장
        promotions: [],
        alldiscount: 0,
    },
    mutations: {
        CHANGE_PDT(state, pdt) {
            state.product = pdt;
        },
        CHANGE_TABS(state, tabs) {
            state.tabs = tabs;
        },

        CHANGE_UPLCY(state, data) {
            // state.polNo = data[0].no;
            state.uPolicies = data;
        },
        CHANGE_APLCY(state, data) {
            state.aPolicies = data;
        },
        SET_STARTDATE(state, startdate) {
            state.startdate = startdate;
        },
        SET_ENDDATE(state, enddate) {
            state.enddate = enddate;
        },
        SET_PROMOTION(state, promotions) {
            state.promotions = promotions;
        },
        ADD_ALLDC(state, alldc) {
            state.alldiscount = state.alldiscount + alldc;
        },
        DEL_ALLDC(state) {
            state.alldiscount = 0;
        }
    },
    actions: {

    },
    getters: {

    }
}