// ==========================
// PRODUCT CLASS & DATA
// ==========================
class Product {
    constructor(id, name, price, image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }
}

// 10 sample products
const products = [
    new Product(1, "Wireless Headphones", 59.99, "img1.jpg"),
    new Product(2, "Smart Watch", 129.99, "img2.jpg"),
    new Product(3, "Bluetooth Speaker", 39.99, "img3.jpg"),
    new Product(4, "Gaming Mouse", 25.99, "img4.jpg"),
    new Product(5, "Mechanical Keyboard", 89.99, "img5.jpg"),
    new Product(6, "Laptop Stand", 19.99, "img6.jpg"),
    new Product(7, "USB Hub", 14.99, "img7.jpg"),
    new Product(8, "Webcam", 49.99, "img8.jpg"),
    new Product(9, "Monitor", 199.99, "img9.jpg"),
    new Product(10, "External SSD", 99.99, "img10.jpg")
];

// ==========================
// CART STATE
// ==========================
let cart = [];


// ==========================
// TASK 2: RENDER PRODUCTS
// ==========================
const productContainer = document.querySelector('.product-grid');

if (productContainer) {
    products.forEach(product => {
        const card = document.createElement('article');

        const img = document.createElement('img');
        img.setAttribute('src', product.image);

        const name = document.createElement('h3');
        name.textContent = product.name;

        const price = document.createElement('p');
        price.textContent = `$${product.price}`;

        const button = document.createElement('button');
        button.textContent = "Add to Cart";
        button.setAttribute('data-id', product.id);

        card.appendChild(img);
        card.appendChild(name);
        card.appendChild(price);
        card.appendChild(button);

        productContainer.appendChild(card);
    });
}


// ==========================
// TASK 3: EVENT DELEGATION
// ==========================
document.body.addEventListener('click', function (event) {
    if (event.target.tagName === "BUTTON" && event.target.textContent === "Add to Cart") {

        const id = parseInt(event.target.getAttribute('data-id'));

        const product = products.find(p => p.id === id);

        const existing = cart.find(item => item.id === id);

        if (existing) {
            existing.quantity++;
        } else {
            cart.push({ ...product, quantity: 1 });
        }

        renderCart();

        // animation
        const card = event.target.parentElement;
        card.classList.add('fade-in');

        setTimeout(() => {
            card.classList.remove('fade-in');
        }, 500);
    }
});


// ==========================
// RENDER CART
// ==========================
function renderCart() {
    const cartList = document.querySelector('.cart-list');
    const totalDisplay = document.querySelector('.total');

    if (!cartList) return;

    cartList.innerHTML = "";

    cart.forEach(item => {
        const li = document.createElement('li');

        const text = document.createElement('span');
        text.textContent = `${item.name} - $${item.price}`;

        const qty = document.createElement('input');
        qty.type = "number";
        qty.value = item.quantity;
        qty.min = 0;
        qty.setAttribute('data-id', item.id);

        li.appendChild(text);
        li.appendChild(qty);

        cartList.appendChild(li);
    });

    // total using reduce
    const total = cart.reduce((sum, item) => {
        return sum + (item.price * item.quantity);
    }, 0);

    if (totalDisplay) {
        totalDisplay.textContent = `Total: $${total.toFixed(2)}`;
    }
}


// ==========================
// QUANTITY CHANGE
// ==========================
document.body.addEventListener('change', function (event) {
    if (event.target.type === "number") {

        const id = parseInt(event.target.getAttribute('data-id'));
        const value = parseInt(event.target.value);

        if (value === 0) {
            cart = cart.filter(item => item.id !== id);
        } else {
            const item = cart.find(i => i.id === id);
            if (item) item.quantity = value;
        }

        renderCart();
    }
});


// ==========================
// TASK 4: FORM VALIDATION
// ==========================
const form = document.querySelector('form');

if (form) {
    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const name = document.querySelector('#name');
        const email = document.querySelector('#email');

        let valid = true;

        [name, email].forEach(input => {
            if (input.value === "") {
                input.classList.add('error');
                valid = false;
            } else {
                input.classList.remove('error');
            }
        });

        if (valid) {
            console.log("Form submitted successfully!");
            window.location.href = "thankyou.html";
        }
    });
}


// ==========================
// TASK 5: USER ACCOUNT
// ==========================
const currentUser = {
    name: "Jerome",
    orderHistory: [
        { date: "2026-03-01", total: 150, items: ["Mouse", "Keyboard"] },
        { date: "2026-03-10", total: 60, items: ["Headphones"] }
    ]
};

const greeting = document.querySelector('.greeting');

if (greeting) {
    greeting.textContent = `Welcome, ${currentUser.name}!`;
}


// ==========================
// ORDER DETAILS EXPANSION
// ==========================
document.querySelectorAll('summary').forEach((summary, index) => {
    summary.addEventListener('click', function () {

        const details = summary.parentElement;

        if (!details.dataset.loaded) {

            const order = currentUser.orderHistory[index];

            const content = document.createElement('p');
            content.textContent = `Date: ${order.date}, Total: $${order.total}, Items: ${order.items.join(", ")}`;

            details.appendChild(content);

            details.dataset.loaded = "true";
        }
    });
});