import { subscribeOneList } from "@/api/shr/subscribe";

export default {
    namespaced: true,
    state: {
        subscribeOneList: [],
    },
    mutations: {
        selectOneSubscribe(state, payload) {
            state.subscribeOneList = payload;
        },
    },
    actions: {
        // 해당 구독 정책 출력하기 위해 사용
        async selectOneSubscribe(context, payload) {
            const { data } = await subscribeOneList({ subscribeNo: payload });
            console.log('data값 ? : ' , data);
            context.commit('selectOneSubscribe', data[0]);
        },
    },
    getters: {
        getSubscribeOneList(state) {
            return state.subscribeOneList;
        },
    }
}