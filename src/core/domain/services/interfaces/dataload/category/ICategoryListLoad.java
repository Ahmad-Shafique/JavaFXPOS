package core.domain.services.interfaces.dataload.category;

import core.domain.model.entities.Category;
import core.domain.services.interfaces.dataload.IDataLoad;

import java.util.List;

public interface ICategoryListLoad  extends IDataLoad{
    void pushData(List<Category> catList);
}
