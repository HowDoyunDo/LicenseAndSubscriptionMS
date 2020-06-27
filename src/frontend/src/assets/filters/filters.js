import moment from 'moment';

export function formatDate(value) {
    return moment(value).format("YYYY.MM.DD");
}
export function formatPrice(value) {
    if (!value) return "";
    return value.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
}