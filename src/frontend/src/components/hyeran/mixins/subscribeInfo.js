export const subscribeMixin = {
    data() {
        return {
            format: "",
            productList: "",
            policy_title: "",
            visible: "",
            standard: "",
            max_count: "",
            selected: '',
            list:'',
        }
    },
    methods: {
        // 구독 정책 폼 유효성 검사
        formValidation() {
            console.log(this.max_count);
            if (
                this.format == "" ||
                this.productList == "" ||
                this.selected == "" ||
                this.policy_title == "" ||
                this.visible == "" ||
                this.standard == "" ||
                this.max_count === ""
            ) {
                alert("필수 입력 값을 입력해주세요");
                return true;
            } else {
                return false;
            }
        },
        // 초기값 세팅
        async defaultSetting() {
            // 해당 구독 정책 list
            const resList = await this.$store.getters[
                "subscribeStore/getSubscribeOneList"
            ];
            console.log(resList);
            // default 세팅
            this.policy_title = resList.policy_title;
            this.visible = resList.visible ? true : false;
            if (resList.standard == "U") {
                this.standard = "U";
            } else {
                this.standard = "A";
            }
            this.max_count = resList.max_count;
            this.format = this.comma(resList.price);
            this.url = resList.url;
            this.reg_date = resList.reg_date;
            // 해당 제품 list
            this.list = resList.selectedList;
            this.productPrice = this.comma(this.list[0].price);
            // 
            this.productList = "false";
            this.selected='false';
        },



    },
};