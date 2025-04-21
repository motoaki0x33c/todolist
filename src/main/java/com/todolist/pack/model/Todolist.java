package com.todolist.pack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity // 標記這個類別是一個 JPA 實體，表示它將被映射到資料庫表
@Table(name = "todolists")  // 指定這個實體對應的資料庫表名稱為 "todolists"
@Data   // Lombok 註解，自動生成 getter、setter、toString、equals 和 hashCode 方法
@NoArgsConstructor  // Lombok 註解，自動生成無參數的建構函式
@AllArgsConstructor // Lombok 註解，自動生成包含所有欄位的建構函式
public class Todolist {
    @Id // 標記這個欄位是資料表的主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 指定主鍵的生成策略為自動遞增

    @NotNull
    private Long id;

    @NotBlank(message = "請輸入名稱")
    private String title;

    private String description;

    private Boolean completed = false;

    private LocalDateTime createdAt = LocalDateTime.now();
}
