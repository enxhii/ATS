package ats.converters;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ats.managed_bean.JobBean;
import ats.model.Benefit;

@FacesConverter("benefitConverter")

public class BenefitConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String benefitId) {
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),
				"#{jobbean}", JobBean.class);

		JobBean jobBean = (JobBean) vex.getValue(ctx.getELContext());
		return jobBean.getBenefit(Integer.valueOf(benefitId));
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object benefit) {
		return String.valueOf(((Benefit) benefit).getId());
	}
}
