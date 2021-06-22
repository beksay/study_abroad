package org.infosystema.study_abroad.model.app;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="good_step")
public class GoodStep extends AppModule{
	private static final long serialVersionUID = 1L;

    private Set<Goods> goods;
	
	@OneToMany(mappedBy="module")
	public Set<Goods> getGoods() {
		return goods;
	}
	
	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}
	
}