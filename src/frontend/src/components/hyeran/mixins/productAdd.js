import ProductOneList from "@/components/hyeran/product/ProductOneList.vue";
import { productAllList } from "@/api/shr/product";

export const productAddMixin = {
  data() {
    return {
      productList: "",
      selected: "",
    }
  },
  async created() {
    // 제품 선택의 전체 list
    const { data } = await productAllList();
    this.productList = data;
  },
  mounted() {
    console.log('hello from mixin!');
  },
  components: {
    ProductOneList,
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


  },
};