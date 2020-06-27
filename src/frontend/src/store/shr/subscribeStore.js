import { productOneList } from "@/api/shr/product";
import { subscribeOneList } from "@/api/shr/subscribe";

export default {
    namespaced: true,
    state: {
        productList: [],
        subscribeOneList: [],
        productSum: '',
        productNoList: [],
    },
    mutations: {
        selectOneProduct(state, payload) {
            state.productSum = Number(state.productSum) + Number(payload.price);
            return state.productList.push(payload);
        },
        deleteOneProduct(state, payload) {
            state.productSum = Number(state.productSum) - Number(state.productList[payload].price);
            state.productNoList.splice(payload, 1);
            return state.productList.splice(payload, 1);
        },
        selectOneSubscribe(state, payload) {
            return state.subscribeOneList = payload;
        },
        //제품선택 중복확인
        productIncludes(state, payload) {
            // console.log('중복? : ', state.productNoList.includes(payload));
            state.productNoList.push(payload);
        },
    },
    actions: {
        // 제품 선택
        async selectOneProduct(context, payload) {
            const { data } = await productOneList({ no: payload });
            return context.commit('selectOneProduct', data[0])
        },
        // 제품 삭제
        deleteOneProduct(context, payload) {
            return context.commit('deleteOneProduct', payload);
        },
        // 해당 구독 정책 출력
         async selectOneSubscribe(context, payload) {
            const { data } = await subscribeOneList({ subscribeNo: payload });
            return context.commit('selectOneSubscribe', data[0]);
        },
    },
    getters: {
        getProductList(state) {
            return state.productList;
        },
        getSubscribeOneList(state) { 
            return state.subscribeOneList;
        },
        getProductSum(state) {
            return state.productSum;
        },

    }
}