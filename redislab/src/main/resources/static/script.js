function fetchBook() {
    const bookId = document.getElementById('bookId').value;
    if (!bookId) {
        alert('Please enter a book ID');
        return;
    }

    const startTime = performance.now();

    fetch(`/api/books/${bookId}`)
        .then(response => response.json())
        .then(data => {
            const endTime = performance.now();
            const timeTaken = (endTime - startTime).toFixed(2);

            document.getElementById('bookInfo').innerHTML = `
                <strong>Book Details:</strong><br>
                ID: ${data.id}<br>
                Title: ${data.title}<br>
                Author: ${data.author}<br>
                ISBN: ${data.isbn}<br>
                Price: ${data.price} USD
            `;

            document.getElementById('timingInfo').innerText = `Request completed in ${timeTaken} ms`;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('bookInfo').innerText = 'Book not found.';
            document.getElementById('timingInfo').innerText = '';
        });
}

function clearCache() {
    fetch('/api/books/cache', { method: 'DELETE' })
        .then(() => {
            alert('Cache cleared successfully');
        })
        .catch(error => {
            console.error('Error clearing cache:', error);
        });
}
