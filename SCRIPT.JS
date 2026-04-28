async function fetchProducts() {
    try {
        const response = await fetch("http://localhost:8080/api/products");

        // Check HTTP errors manually
        if (!response.ok) {
            if (response.status === 404) {
                throw new Error("Products not found (404)");
            } else if (response.status === 500) {
                throw new Error("Server error (500)");
            } else {
                throw new Error("Unexpected error: " + response.status);
            }
        }

        const products = await response.json();

        renderProducts(products);

    } catch (error) {
        console.error("Error fetching products:", error.message);
        showErrorState(error.message);
    }
}
