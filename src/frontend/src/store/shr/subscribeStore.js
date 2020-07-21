import { subscribeOneList } from "@/api/shr/subscribe";

export default {
    namespaced: true,
    state: {
        subscribeOneList: [],
        policyList:[],
        policyNoList: [],
        policySum: "",
    },
    mutations: {
        selectOneSubscribe(state, payload) {
            state.subscribeOneList = payload;
        },
        selectSubscribe(state, payload){
            state.policySum = Number(state.policySum) + Number(payload.price);
            state.policyList.push(payload);
        },
        // 정책선택 중복확인
        policyIncludes(state, payload) {
            state.policyNoList.push(payload);
        },
        // 정책선택 전체 초기화
        policyAllDelete(state) {
            state.policyList = [];
            state.policyNoList = [];
            state.policySum = '';
        },
        deleteOnePolicy(state, payload) {
            state.policySum = Number(state.policySum) - Number(state.policyList[payload].price);
            state.policyNoList.splice(payload, 1);
            state.policyList.splice(payload, 1);
        },
    },
    actions: {
        // 해당 구독 정책 출력(1개)
        async selectOneSubscribe(context, payload) {
            const { data } = await subscribeOneList({ subscribeNo: payload });
            context.commit('selectOneSubscribe', data[0]);
        },
        // 해당 구독 정책 출력(여러개)
        async selectSubscribe(context, payload) {
            const { data } = await subscribeOneList({ subscribeNo: payload });
            context.commit('selectSubscribe', data[0]);
        },
         // 해당 정책리스트 삭제
         deleteOnePolicy(context, payload) {
            context.commit('deleteOnePolicy', payload);
        },
    },
    getters: {
        getSubscribeOneList(state) {
            return state.subscribeOneList;
        },
        getPolicyList(state) {
            return state.policyList;
        },
        getPolicySum(state) {
            return state.policySum;
        },
    }
}