// Problem 1: The Strict Type Checker
function checkVariable(input) {
    switch (typeof input) {
        case "string":
            return "string";
        case "number":
            return "number";
        case "boolean":
            return "boolean";
        case "bigint":
            return "bigint";
        case "undefined":
            return "undefined";
        case "object":
            return "object"; // covers null and objects
        default:
            return "unknown";
    }
}


// Problem 2: Secure ID Generator
function generateIDs(count) {
    let result = [];

    for (let i = 0; i < count; i++) {
        if (i === 5) continue; // skip 5
        result.push(`ID-${i}`);
    }

    return result;
}


// Problem 3: The Functional Sum
function calculateTotal(...numbers) {
    return numbers.reduce((total, num) => {
        if (typeof num !== "number") {
            throw new TypeError("Invalid input: All arguments must be numbers");
        }
        return total + num;
    }, 0);
}


// Problem 4: Leaderboard Filter
function getTopScorers(playerList) {
    return playerList
        .filter(player => player.score > 8)
        .map(player => player.name)
        .join(", ");
}


// Problem 5: The Private Inventory
class Item {
    #discount = 0.1;

    constructor(name, price) {
        this.name = name;
        this.price = price;
    }

    get finalPrice() {
        return this.price - (this.price * this.#discount);
    }
}


// Problem 6: Robust Division
function safeDivide(a, b) {
    try {
        if (b === 0) {
            throw new Error("Cannot divide by zero");
        }
        return a / b;
    } catch (error) {
        return error.message;
    } finally {
        console.log("Operation attempted");
    }
}


// =======================
// TESTING OUTPUT
// =======================

console.log("Problem 1:");
console.log(checkVariable("hello")); // string
console.log(checkVariable(10)); // number
console.log(checkVariable(null)); // object

console.log("\nProblem 2:");
console.log(generateIDs(7)); // skip ID-5

console.log("\nProblem 3:");
console.log(calculateTotal(1, 2, 3)); // 6
// console.log(calculateTotal(1, "a", 3)); // error

console.log("\nProblem 4:");
const players = [
    { name: "Alice", score: 10 },
    { name: "Bob", score: 5 },
    { name: "Charlie", score: 12 }
];
console.log(getTopScorers(players)); // Alice, Charlie

console.log("\nProblem 5:");
const item1 = new Item("Laptop", 1000);
console.log(item1.finalPrice); // 900

console.log("\nProblem 6:");
console.log(safeDivide(10, 2)); // 5
console.log(safeDivide(10, 0)); // error message
