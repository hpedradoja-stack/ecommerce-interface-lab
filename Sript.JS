function renderProducts(products) {
    const main = document.querySelector("main");
    main.innerHTML = "";

    // Empty state handling
    if (!products || products.length === 0) {
        main.innerHTML = "<p>No products available.</p>";
        return;
    }

    // Create product cards dynamically
    products.forEach(product => {
        const card = document.createElement("div");
        card.className = "product-card";

        card.innerHTML = `
            <h3>${product.name}</h3>
            <p>Price: ₱${product.price}</p>
            <p>Stock: ${product.stock}</p>
            <p>Category: ${product.category?.name || "N/A"}</p>
        `;

        main.appendChild(card);
    });
}
