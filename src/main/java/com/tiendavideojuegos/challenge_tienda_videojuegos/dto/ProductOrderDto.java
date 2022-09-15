package com.tiendavideojuegos.challenge_tienda_videojuegos.dto;

public class ProductOrderDto {

    private long idProducto;

    private Integer productQuantity;

    public ProductOrderDto() {
    }

    public ProductOrderDto(long idProducto, Integer productQuantity) {
        this.idProducto = idProducto;
        this.productQuantity = productQuantity;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
