package core.domain.services.interfaces;

import core.domain.model.entities.Category;

import java.util.List;

public interface ICategoryCRUD extends ICRUD<Category> {
    public List<String> getAllCategoryNames();
}
