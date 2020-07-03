import { productOneList } from '@/api/shr/product';
import ProductOneList from "@/components/hyeran/product/ProductOneList.vue";

export const promotionMixin = {
    data() {
        return {
            title: "",
            product_no: "",
            discount: "",
            start_date: "",
            start_date_time: "",
            end_date: "",
            end_date_time: "",
            type: "",
            promotionProduct: [],
        }
    },
    components: {
        ProductOneList
    },
    methods: {
        // 프로모션 폼 유효성 검사
        promotionFormValidation() {
            if (
                this.title == "" ||
                this.productList == "" ||
                this.selected == "" ||
                this.discount == "" ||
                this.start_date == "" ||
                this.start_date_time == "" ||
                this.end_date == "" ||
                this.end_date_time == "" ||
                this.type == ""
            ) {
                alert("필수 입력 값을 입력해주세요");
                return true;
            } else {
                return false;
            }
        },

        // 초기값 세팅
        async defaultSetting() {
            // 해당 프로모션 list
            const list = await this.$store.getters[
                "promotionStore/getPromotionOneList"
            ];
            this.title = list.title;
            this.product_no = list.product_no;
            this.discount = list.discount;
            this.start_date = list.start_date;
            this.start_date_time = list.start_date_time;
            this.end_date = list.end_date;
            this.end_date_time = list.end_date_time;
            this.type = list.type;
            // 해당 프로모션의 제품 
            const { data } = await productOneList({ no: this.product_no });
            this.promotionProduct = data;
        },


        // 할인율 입력폼 체크
        discountPriceForm(e) {
            if (this.discount < 0 || this.discount > 100) {
                alert("입력값은 0 이상 100 이하여야 합니다.");
                e.target.value = "";
                this.discount = "";
                this.discountPrice = 0;
            } else {
                // 할인율
                this.discountPrice = this.discount / 100;
            }
        }

    },

};