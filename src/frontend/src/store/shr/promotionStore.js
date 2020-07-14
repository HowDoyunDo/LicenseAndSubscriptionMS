import { promotionOneList, promotionPolicyOneList } from "@/api/shr/promotion";

export default {
    namespaced: true,
    state: {
        promotionList: "",
        promotionPolicyList:"",

    },
    mutations: {
        selectOnePromotion(state, payload) {
            state.promotionList = payload;
        },
        selectOnePromotionPolicy(state, payload) {
            state.promotionPolicyList = payload;
        },
    },
    actions: {
        // 해당 프로모션 가져오기 
        async selectOnePromotion(context, payload) {
            const { data } = await promotionOneList({ promotion_no: payload });
            context.commit('selectOnePromotion', data[0]);
        },
        async selectOnePromotionPolicy(context, payload) {
            const { data } = await promotionPolicyOneList({ promotion_no: payload });
            context.commit('selectOnePromotionPolicy', data[0]);
        }
    },
    getters: {
        getPromotionOneList(state) {
            return state.promotionList;
        },
        getPromotionPolicyOneList(state) {
            return state.promotionPolicyList;
        }
    }
}
