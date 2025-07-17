package kr.hhplus.be.server.Controller.mock;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/mock")
public class MockEcommerceController {

    // 1) 잔액 충전
    @PostMapping("/point/charge")
    @Operation(summary = "잔액 충전", description = "포인트 잔액을 충전합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "충전 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name    = "충전 결과 예시",
                                    value   = "{\n" +
                                            "  \"userId\": \"jojoa\",\n" +
                                            "  \"totalBalance\": 105000,\n" +
                                            "  \"chargedAmount\": 5000,\n" +
                                            "  \"regDate\": \"2025-07-18T10:00:00\"\n" +
                                            "}"
                            )
                    )
            )
    })
    public ResponseEntity<Map<String, Object>> charge(
            @RequestBody(
                    description = "충전 요청 예시",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name  = "충전 요청 예시",
                                    value = "{ \"userId\": \"jojoa\", \"amount\": 5000 }"
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody Map<String, Object> req
    ) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("userId", "jojoa");
        resp.put("totalBalance", 105000);
        resp.put("chargedAmount", 5000);
        resp.put("regDate", LocalDateTime.of(2025, 7, 18, 10, 0));
        return ResponseEntity.ok(resp);
    }

    // 2) 잔액 조회
    @GetMapping("/point/getAmount")
    @Operation(summary = "잔액 조회", description = "포인트 잔액을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name  = "잔액 조회 응답 예시",
                                    value = "{\n" +
                                            "  \"userId\": \"jojoa\",\n" +
                                            "  \"totalBalance\": 100000\n" +
                                            "}"
                            )
                    )
            )
    })
    public ResponseEntity<Map<String, Object>> getAmount(
            @RequestParam String userId
    ) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("userId", userId);
        resp.put("totalBalance", 100000);
        return ResponseEntity.ok(resp);
    }

    // 3) 상품 조회
    @GetMapping("/product/{productId}")
    @Operation(summary = "상품 조회", description = "상품 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name  = "상품 조회 응답 예시",
                                    value = "{\n" +
                                            "  \"productId\": \"1234\",\n" +
                                            "  \"price\": 19900,\n" +
                                            "  \"quantity\": 20\n" +
                                            "}"
                            )
                    )
            )
    })
    public ResponseEntity<Map<String, Object>> getProductById(
            @PathVariable String productId
    ) {
        Map<String, Object> prod = new HashMap<>();
        prod.put("productId", productId);
        prod.put("price", 19900);
        prod.put("quantity", 20);
        return ResponseEntity.ok(prod);
    }

    // 4) 주문 생성
    @PostMapping("/order")
    @Operation(summary = "주문 생성", description = "결제 전 주문을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "생성 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name  = "주문 생성 응답 예시",
                                    value = "{\n" +
                                            "  \"orderId\": \"1234\",\n" +
                                            "  \"userId\": \"jojoa\",\n" +
                                            "  \"items\": [ { \"productId\": \"prod-1234\", \"quantity\": 2 } ],\n" +
                                            "  \"status\": \"CREATED\",\n" +
                                            "  \"regDate\": \"2025-07-18T10:00:00\"\n" +
                                            "}"
                            )
                    )
            )
    })
    public ResponseEntity<Map<String, Object>> placeOrder(
            @RequestBody(
                    description = "주문 생성 요청 예시",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name  = "주문 생성 요청 예시",
                                    value = "{\n" +
                                            "  \"userId\": \"jojoa\",\n" +
                                            "  \"items\": [ { \"productId\": \"prod-1234\", \"quantity\": 2 } ]\n" +
                                            "}"
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody Map<String, Object> req
    ) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("orderId", "1234");
        resp.put("userId", "jojoa");
        resp.put("items", req.get("items"));
        resp.put("status", "CREATED");
        resp.put("regDate", LocalDateTime.of(2025, 7, 18, 10, 0));
        return ResponseEntity.ok(resp);
    }

    // 5) 결제 처리
    @PostMapping("/order/{orderId}/payment")
    @Operation(summary = "결제 처리", description = "주문을 결제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "결제 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name  = "결제 응답 예시",
                                    value = "{\n" +
                                            "  \"paymentId\": \"pay-123\",\n" +
                                            "  \"orderId\": \"order-123\",\n" +
                                            "  \"userId\": \"jojoa\",\n" +
                                            "  \"amount\": 20000,\n" +
                                            "  \"paymentStatus\": \"PAID\",\n" +
                                            "  \"regDate\": \"2025-07-18T10:00:00\"\n" +
                                            "}"
                            )
                    )
            )
    })
    public ResponseEntity<Map<String, Object>> processPayment(
            @PathVariable String orderId,
            @RequestBody(
                    description = "결제 요청 예시",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name  = "결제 요청 예시",
                                    value = "{ \"userId\": \"jojoa\", \"amount\": 20000 }"
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody Map<String, Object> req
    ) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("paymentId", "pay-123");
        resp.put("orderId", orderId);
        resp.put("userId", "jojoa");
        resp.put("amount", 20000);
        resp.put("paymentStatus", "PAID");
        resp.put("regDate", LocalDateTime.of(2025, 7, 18, 10, 0));
        return ResponseEntity.ok(resp);
    }

    // 6) 쿠폰 발급
    @PostMapping("/coupon/first-come")
    @Operation(summary = "쿠폰 발급", description = "선착순으로 쿠폰을 발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "발급 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name  = "쿠폰 발급 응답 예시",
                                    value = "{\n" +
                                            "  \"couponId\": \"coupon-123\",\n" +
                                            "  \"userId\": \"jojoa\",\n" +
                                            "  \"code\": \"c-1234\",\n" +
                                            "  \"regDate\": \"2025-07-18T10:00:00\"\n" +
                                            "}"
                            )
                    )
            )
    })
    public ResponseEntity<Map<String, Object>> issueFirstComeCoupon(
            @RequestBody(
                    description = "쿠폰 발급 요청 예시",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name  = "쿠폰 발급 요청 예시",
                                    value = "{ \"userId\": \"jojoa\" }"
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody Map<String, String> req
    ) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("couponId", "coupon-123");
        resp.put("userId", "jojoa");
        resp.put("code", "c-1234");
        resp.put("regDate", LocalDateTime.of(2025, 7, 18, 10, 0));
        return ResponseEntity.ok(resp);
    }

    // 7) 인기 상품 조회
    @GetMapping("/product/top")
    @Operation(summary = "인기 상품 조회", description = "가장 많이 팔린 상품을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name  = "인기 상품 조회 응답 예시",
                                    value = "{\n" +
                                            "  \"topProducts\": [ { \"id\": \"Top1\" }, { \"id\": \"Top2\" } ]\n" +
                                            "}"
                            )
                    )
            )
    })
    public ResponseEntity<Map<String, Object>> getTopSellingProducts(
            @RequestParam int days
    ) {
        List<Map<String, Object>> top = new ArrayList<>();
        top.add(Map.of("id", "Top1"));
        top.add(Map.of("id", "Top2"));
        Map<String, Object> resp = new HashMap<>();
        resp.put("topProducts", top);
        return ResponseEntity.ok(resp);
    }
}
