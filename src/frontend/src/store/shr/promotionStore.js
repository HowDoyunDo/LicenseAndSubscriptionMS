import { promotionOneList } from "@/api/shr/promotion";

export default {
    namespaced: true,
    state: {
        promotionList: "",

    },
    mutations: {
        selectOnePromotion(state, payload) {
            state.promotionList = payload;
        },
    },
    actions: {
        // 해당 프로모션 가져오기 
        async selectOnePromotion(context, payload) {
            const { data } = await promotionOneList({ promotion_no: payload });
            context.commit('selectOnePromotion', data[0]);
        }
    },
    getters: {
        getPromotionOneList(state) {
            return state.promotionList;
        }
    }
}
