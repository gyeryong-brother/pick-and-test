package com.gyeryongbrother.pickandtest.stock.application.rest;

import static org.springframework.http.HttpStatus.CREATED;

import com.gyeryongbrother.pickandtest.stock.application.dto.CreateFavoriteStockRequest;
import com.gyeryongbrother.pickandtest.stock.application.dto.DeleteFavoriteStockRequest;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.DeleteFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.FavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.FavoriteStockQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.FavoriteStockService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite-stocks")
public class FavoriteStockController {

    private final FavoriteStockService favoriteStockService;
    private final FavoriteStockQueryService favoriteStockQueryService;

    @PostMapping
    ResponseEntity<CreateFavoriteStockResponse> createFavoriteStock(
            @RequestBody CreateFavoriteStockRequest createFavoriteStockRequest
    ) {
        CreateFavoriteStockCommand createFavoriteStockCommand = createFavoriteStockRequest.toCommand();
        CreateFavoriteStockResponse createFavoriteStockResponse =
                favoriteStockService.createFavoriteStock(createFavoriteStockCommand);
        return ResponseEntity.status(CREATED)
                .body(createFavoriteStockResponse);
    }

    @GetMapping
    ResponseEntity<List<FavoriteStockResponse>> findAllFavoriteStocks() {
        List<FavoriteStockResponse> favoriteStockResponses =
                favoriteStockQueryService.findAllFavoriteStocksByMemberId(1L);
        return ResponseEntity.ok(favoriteStockResponses);
    }

    @GetMapping("/stocks/{stockId}")
    ResponseEntity<FavoriteStockResponse> findFavoriteStockByStockId(@PathVariable Long stockId) {
        FavoriteStockResponse response = favoriteStockQueryService.findFavoriteStockByStockIdAndMemberId(stockId, 1L);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{favoriteStockId}")
    ResponseEntity<Void> deleteFavoriteStock(
            @PathVariable Long favoriteStockId,
            @RequestBody DeleteFavoriteStockRequest request
    ) {
        DeleteFavoriteStockCommand command = request.toCommand(favoriteStockId);
        favoriteStockService.deleteFavoriteStock(command);
        return ResponseEntity.noContent().build();
    }
}
