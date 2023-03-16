const search = document.querySelector('input[type="search"]');
const results = document.querySelector('.search-results');

function searchHandler() {
    console.log('searchHandler called');
    const searchText = search.value.toLowerCase();
    const articles = document.querySelectorAll('article');
    let count = 0;
    let html = '';

    articles.forEach((article) => {
        const articleText = article.textContent.toLowerCase();

        if (articleText.includes(searchText)) {
            article.style.display = 'block';
            html += `
        <div class="search-result">
          <h3>${article.querySelector('h3').textContent}</h3>
          <p>${article.querySelector('p').textContent}</p>
        </div>
      `;
            count++;
        } else {
            article.style.display = 'none';
        }
    });

    if (count === 0) {
        html = '<p>No results found</p>';
    }

    results.innerHTML = html;
}

search.addEventListener('input', searchHandler);