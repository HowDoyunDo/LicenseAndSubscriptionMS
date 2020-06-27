import {instance}  from '../index';

function promotionAllList(){
    return instance.get('promotionAllList');
} 
export {promotionAllList};