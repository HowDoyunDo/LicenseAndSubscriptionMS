import { productOneList } from "@/api/shr/product";


export default {
    namespaced: true,
    state: {
        productList: [],
        productSum: '',
        productNoList: [],
    },
    mutations: {
        selectOneProduct(state, payload) {
            state.productSum = Number(state.productSum) + Number(payload.price);
            state.productList.push(payload);
        },
        deleteOneProduct(state, payload) {
            state.productSum = Number(state.productSum) - Number(state.productList[payload].price);
            state.productNoList.splice(payload, 1);
            state.productList.splice(payload, 1);
        },
        //제품선택 중복확인
        productIncludes(state, payload) {
            // console.log('중복? : ', state.productNoList.includes(payload));
            state.productNoList.push(payload);
        },
        // 제품선택 전체 초기화
        productAllDelete(state){
            state.productNoList=[];
            state.productList=[];
            state.productSum='';
        },
       
    },
    actions: {
        // 제품 선택
        async selectOneProduct(context, payload) {
            const { data } = await productOneList({ no: payload });
            context.commit('selectOneProduct', data[0])
        },
        // 제품 삭제
        deleteOneProduct(context, payload) {
            context.commit('deleteOneProduct', payload);
        },

    },
    getters: {
        getProductList(state) {
            return state.productList;
        },
        getProductSum(state) {
            return state.productSum;
        },
        getProductOne(state) {
            return state.productOne;
        },
        

    }
}