
export default  {    
    namespaced: true,
    state: {
        // 솔루션, 제품 선택 정보
        solution: '',
        product: '',

        // 제품 소개 페이지 탭 가시 여부
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

        // 선택 솔루션에 대한 정책정보 저장
        uPolicies: [],
        aPolicies: [],
        polno: '',

        // 구독 신청 시 구독 시작일
        startdate: '',
        enddate: '',

        // 프로모션 정보 저장
        promotions: [],
        alldiscount: 0,
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
            state.polNo = data[0].no;
            return state.uPolicies = data;
        },
        CHANGE_APLCY(state, data) {
            return state.aPolicies = data;
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