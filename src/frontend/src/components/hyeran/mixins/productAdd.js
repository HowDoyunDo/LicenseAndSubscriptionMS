import ProductOneList from "@/components/hyeran/product/ProductOneList.vue";
import SubscribeOneList from "@/components/hyeran/subscribe/SubscribeOneList.vue";
import { productAllList } from "@/api/shr/product";
import { subscribeAllList } from "@/api/shr/subscribe";

export const productAddMixin = {
  data() {
    return {
      productList: "",
      policyList : "",
      selected: "",
      selectedPolicy : "",
    }
  },
  async created() {
    // 제품 선택의 전체 list
    const { data } = await productAllList();
    this.productList = data;

    // 정책 선택의 전체 list
    const res = await subscribeAllList();
    this.policyList = res.data;
  },
  components: {
    ProductOneList,
    SubscribeOneList
  },
  methods: {
    // 선택 제품 출력
    async addProduct() {
      // 제품 포함 여부
      const result = await this.$store.state.productStore_shr.productNoList.includes(
        this.selected
      );

      if (result) {
        alert("이미 선택한 제품입니다.");
      } else {
        await this.$store.commit(
          "productStore_shr/productIncludes",
          this.selected
        );
        await this.$store.dispatch(
          "productStore_shr/selectOneProduct",
          this.selected
        );
        this.productSum = await this.$store.getters[
          "productStore_shr/getProductSum"
        ];
      }
    },
    // 선택 정책 출력
    async addPolicy() {
      // 정책 포함 여부
      const result = await this.$store.state.subscribeStore.policyNoList.includes(
        this.selectedPolicy
      );

      if (result) {
        alert("이미 선택한 정책입니다.");
      } else {
        await this.$store.commit(
          "subscribeStore/policyIncludes",
          this.selectedPolicy
        );
        await this.$store.dispatch(
          "subscribeStore/selectSubscribe",
          this.selectedPolicy
        );
        this.policySum = await this.$store.getters[
          "subscribeStore/getPolicySum"
        ];
      }
    },


  },
};