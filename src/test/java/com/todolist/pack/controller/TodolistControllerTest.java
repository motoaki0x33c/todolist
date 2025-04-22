package com.todolist.pack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.pack.model.Todolist;
import com.todolist.pack.service.TodolistService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest 只載入 Controller 相關的配置，其他服務會自動被模擬，不會載入資料庫配置等
@WebMvcTest(TodolistController.class)
public class TodolistControllerTest {
    // MockMvc，用於模擬 HTTP 請求
    @Autowired
    private MockMvc mockMvc;

    // 用於 JSON 序列化和反序列化
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TodolistService todolistService;    // 服務被自動模擬

    @Test
    void testCreateTodo() throws Exception {
        Todolist input = new Todolist(null, "測試標題", "測試內容", false, null);   // 發送到 API 的資料
        Todolist output = new Todolist(1L, "測試標題", "測試內容", false, null);    // 預期從服務返回的資料

        // 設定當調用 createTodolist 方法時返回 output 物件
        // any(Todolist.class) 表示接受任何 Todolist 類型的參數
        Mockito.when(todolistService.createTodolist(any(Todolist.class))).thenReturn(output);

        // 執行測試
        mockMvc.perform(
                    post("/todolist")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("測試標題"));
    }
}
