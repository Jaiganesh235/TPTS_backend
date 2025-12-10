package com.quadcore.tpts.tptsExceptions;

public class CategoryDeletionException extends RuntimeException{
    private Long categoryId;
    private int affectedProductCount;

    public CategoryDeletionException(String message) {
        super(message);
    }

    public CategoryDeletionException(Long categoryId, int affectedProductCount) {
        super(String.format("category with %d id is having %d product under this therefore it is not advicable to delted this ",categoryId,affectedProductCount));
        this.categoryId = categoryId;
        this.affectedProductCount = affectedProductCount;
    }



}
