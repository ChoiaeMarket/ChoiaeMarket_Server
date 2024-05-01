package com.choiaemarket.choiaemarket_server.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListItem {
    private int productNumberl;
    private String idoll;
    private String typel;
    private String namel;
    private String imagel;
    private int priceAvgl;
    private int soldCountl;
    private int favoriteCountl;
}
