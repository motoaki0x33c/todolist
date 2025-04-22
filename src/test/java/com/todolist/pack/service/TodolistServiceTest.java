package com.todolist.pack.service;

import com.todolist.pack.model.Todolist;
import com.todolist.pack.repository.TodolistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

// @SpringBootTest 會載入整個 Spring 應用程式，包括資料庫配置、安全配置等。但使用 @MockBean 可以模擬特定的依賴
@SpringBootTest
public class TodolistServiceTest {
    @Autowired
    private TodolistService todolistService;    // 實際的服務
    
    @MockBean
    private TodolistRepository todoRepository;

    @Test
    void testCreateTodolist() {
        Todolist input = new Todolist(null, "測試標題", "測試內容", false, null);
        Todolist expected = new Todolist(1L, "測試標題", "測試內容", false, null);
        
        Mockito.when(todoRepository.save(any(Todolist.class))).thenReturn(expected);
        
        Todolist result = todolistService.createTodolist(input);
        
        // 4. 驗證結果
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("測試標題", result.getTitle());
        Assertions.assertEquals("測試內容", result.getDescription());
        Assertions.assertFalse(result.getCompleted());
        
        // 5. 驗證互動
        Mockito.verify(todoRepository, Mockito.times(1)).save(any(Todolist.class));
    }

    @Test
    void testUpdateTodolist() {
        // 1. 準備測試資料
        Long id = 1L;
        Todolist existing = new Todolist(id, "舊標題", "舊內容", false, null);
        Todolist update = new Todolist(null, "新標題", "新內容", true, null);
        Todolist expected = new Todolist(id, "新標題", "新內容", true, null);
        
        // 2. 設定模擬行為
        Mockito.when(todoRepository.findById(id)).thenReturn(Optional.of(existing));
        Mockito.when(todoRepository.save(any(Todolist.class))).thenReturn(expected);
        
        // 3. 執行測試
        Todolist result = todolistService.updateTodolist(id, update);
        
        // 4. 驗證結果：檢查返回的物件是否符合預期
        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals("新標題", result.getTitle());
        Assertions.assertEquals("新內容", result.getDescription());
        Assertions.assertTrue(result.getCompleted());
        
        // 5. 驗證互動：檢查方法是否被正確呼叫
        Mockito.verify(todoRepository, Mockito.times(1)).findById(id);  // 檢查是否呼叫了 findById
        Mockito.verify(todoRepository, Mockito.times(1)).save(any(Todolist.class));  // 檢查是否呼叫了 save
    }
}
