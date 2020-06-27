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
        }
    },
    methods: {
        // 구독 정책 폼 유효성 검사
        formValidation() {
            if (
                this.format == "" ||
                this.productList == "" ||
                this.selected == "" ||
                this.policy_title == "" ||
                this.visible == "" ||
                this.standard == "" ||
                this.max_count == ""
            ) {
                alert("필수 입력 값을 입력해주세요");
                console.log(':',this.format ,':', this.productList, ':',this.selected,':', this.policy_title)
                console.log(this.visible,':', this.standard,':', this.max_count)
                return true;
            } else {
                return false;
            }
        },
        // 초기값 세팅
        async defaultSetting() {
            // 해당 구독 정책 list
            const list = await this.$store.getters[
                "subscribeStore/getSubscribeOneList"
            ];

            // default 세팅
            this.policy_title = list.policy_title;
            this.visible = list.visible ? true : false;
            if (list.standard == "U") {
                this.standard = "U";
            } else {
                this.standard = "A";
            }
            this.max_count = list.max_count;
            this.format = this.comma(list.price);
            this.url = list.url;
            this.reg_date = list.reg_date;
            // 해당 제품 list
            this.list = list.selectedList;
            this.productPrice = this.comma(this.list[0].price);
            // 
            this.productList = "false";
            this.selected='false';
        },



    },
};