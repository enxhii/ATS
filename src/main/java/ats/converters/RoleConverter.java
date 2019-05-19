package ats.converters;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ats.managed_bean.UserBean;
import ats.model.*;




@FacesConverter("roleConverter")
public class RoleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String roleId) {
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),
				"#{userbean}", UserBean.class);

		UserBean userbean = (UserBean) vex.getValue(ctx.getELContext());
		return userbean.getRole(Integer.valueOf(roleId));
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object role) {
		return String.valueOf(((Role) role).getId());
	}

}