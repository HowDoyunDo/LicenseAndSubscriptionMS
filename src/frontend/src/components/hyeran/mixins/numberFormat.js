export const formatMixin = {
    methods: {
        numberFormat(event) {
            var regexp = /[^0-9]/gi;
            let num = event.target.value;
            event.target.value = this.comma(num.replace(regexp, ""));
            this.format = this.comma(this.uncomma(num));
        },
        // 콤마 찍기
        comma(str) {
            str = String(str);
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,");
        },
        // 콤마 풀기
        uncomma(str) {
            str = String(str);
            return str.replace(/[^\d]+/g, "");
        },
    }
}
