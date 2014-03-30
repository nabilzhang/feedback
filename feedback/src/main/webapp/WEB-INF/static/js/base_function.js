/**
 * 获取错误信息
 * @param err
 * @returns
 */
function getOneErrorStr(err){
	if(typeof err ==='object'){
		if(err.length > 0){
			return getFieldDisc(err[0].field) + err[0].message;
		}
	}else {
		return err;
	}
}

var form_field_constants = {
		'email':'邮箱',
		'password':'密码',
		'rePassword':'密码重复'
};

/**
 * 获取form的key 与显示对应
 * @param field
 * @returns
 */
function getFieldDisc(field){
	return form_field_constants[field];
}

