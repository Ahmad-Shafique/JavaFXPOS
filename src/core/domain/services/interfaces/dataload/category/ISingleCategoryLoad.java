package core.domain.services.interfaces.dataload.category;

import core.domain.model.entities.Category;
import core.domain.services.interfaces.dataload.IDataLoad;

public interface ISingleCategoryLoad extends IDataLoad{
    void pushData(Category category);
}
