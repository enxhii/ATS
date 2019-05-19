package ats.converters;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ats.managed_bean.JobBean;
import ats.model.JobCategory;

@FacesConverter("categoryConverter")

public class CategoryConverter  implements Converter{
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String categoryId) {
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),
				"#{jobbean}", JobBean.class);

		JobBean jobbean = (JobBean) vex.getValue(ctx.getELContext());
		return jobbean.getCategory(Integer.valueOf(categoryId));
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object category) {
		return String.valueOf(((JobCategory) category).getId());
	}
}
