package com.example.shop.domain;

import com.example.shop.exception.NotEnoughStockException;
import lombok.*;

import javax.persistence.*;


@Entity
@Table
@Setter @Getter @ToString
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor // 모든 필드를 파라미터로 가지는 생성자를 생성
public class Item {

  @Id // javax.persistence.Id를 선택해야
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment, 정수형으로 선언한 곳에만 가능
  private Long id;

  @Column(length = 100, nullable = false)
  private String name;

  private Integer price;
  private Integer qty;

  public Item(String name, Integer price, Integer qty) {
    this.name = name;
    this.price = price;
    this.qty = qty;
  }

  // public Item(Long id, String name, Integer price, Integer qty) {
  //   this.id = id;
  //   this.name = name;
  //   this.price = price;
  //   this.qty = qty;
  // }

  // @Override
  // public String toString() {
  //   return "Item [id=" + id + ", name=" + name + ", price=" + price + "]";
  // }

  /**
   * 주문한 수량만큼 재고수량을 감소
   * @param orderQty
   */
  public void removeStock(int orderQty){
    if(this.qty - orderQty < 0){
      throw new NotEnoughStockException("재고 수량이 부족합니다.");
    }
    this.qty -= orderQty;
  }

  /**
   * 취소한 수량만큼 재고수량이 증가
   * @param orderCancelQty
   */
  public void addStock(int orderCancelQty){
    this.qty += orderCancelQty;
  }
}
