package ir.science.essay.essayfeature.usecase;

import ir.science.essay.entities.Category;

public interface FindCategoryByUserUseCase {
    Category get(int categoryId);
}
