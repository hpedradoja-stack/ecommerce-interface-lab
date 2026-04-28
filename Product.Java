/**
 * Represents a product entity stored in the database.
 * Contains product details such as name, price, stock, and category.
 */
@Entity
public class Product {

/**
 * Represents a product category in the system.
 * A category can have multiple products.
 */
@Entity
public class Category {

/**
 * Fetches product data from the backend API.
 * Uses async/await for asynchronous handling.
 * try/catch ensures errors are handled properly.
 */
async function fetchProducts() {
    try {
        const response = await fetch("http://localhost:8080/api/products");

        // Check if HTTP response is successful
        if (!response.ok) {
            throw new Error("HTTP error: " + response.status);
        }

        const data = await response.json();
        renderProducts(data);

    } catch (error) {
        // Logs error for debugging in browser console
        console.error("Fetch error:", error.message);
    }
}
/**
 * Fetch products from backend API using async/await.
 * Handles errors using try/catch and checks response status.
 */
async function fetchProducts() {
    try {
        const response = await fetch("http://localhost:8080/api/products");

        if (!response.ok) {
            throw new Error("HTTP Error: " + response.status);
        }

        const data = await response.json();
        renderProducts(data);

    } catch (error) {
        console.error("Fetch error:", error.message);
    }
}
