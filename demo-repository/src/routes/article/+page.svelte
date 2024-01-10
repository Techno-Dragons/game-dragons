<script>
    import {page} from '$app/stores';
    import axios from 'axios';
    import {onMount} from 'svelte';
    import WriteArticle from './write/+page.svelte';

    let promise = Promise.resolve([]);
    let pageList = $state({
        articles: [{}],
        currentPage: 0,
        totalPages: 0,
        totalElements: 0,
        pageSize: 10
    });

    let memberInfo = null;

    function login() {
        return new Promise(async (resolve, reject) => {
            try {
                let res = await axios.post(
                    `https://gamedragons.api.bi3a.app/member/login`,
                    {username: '123', password: '123'},
                    {withCredentials: true}
                );
                console.log(res);
            } catch (error) {
                reject(error);
            } finally {
                console.log('done');
            }
        });
    }

    let isPost = $state(false);
    let inputTitle = $state('');
    let inputContent = $state('');

    let totalPages = $state();
    let totalElements = $state();

    let username = $state(null);

    function formatDateTime(dateTimeString) {
        const date = new Date(dateTimeString);
        const options = {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit'
        };
        return date.toLocaleDateString('ko-KR', options);
    }

    function loadArticleList(page) {
        return new Promise(async (resolve, reject) => {
            try {
                const res = await axios.get(`https://gamedragons.api.bi3a.app/article?page=${page}`);
                console.log(res);
                pageList.articles = res.data.data.content;
                pageList.totalPages = res.data.data.totalPages;
                pageList.currentPage = res.data.data.number;
                pageList.totalElements = res.data.data.totalElements;
                console.log(pageList);
                resolve(pageList.articles);
                console.log(pageList.articles[0].title);
            } catch (err) {
                console.log('err');
                reject(err);
            } finally {
                console.log('done');
            }
        });
    }

    async function postArticle() {
        const res = await axios.post(
            `https://gamedragons.api.bi3a.app/article`,
            {title: inputTitle, content: inputContent},
            {withCredentials: true}
        );
        console.log(res);
        console.log(inputTitle);
        console.log(inputContent);
        window.location.href = `/article/${res.data.data}`;
    }

    function changePage(page) {
        pageList.currentPage = page;
        console.log(pageList.currentPage);
        loadArticleList(pageList.currentPage);
    }

    function createPageArray(currentPage, totalPages) {
        let pages = [];

        const range = 5;
        let start = Math.max(currentPage - range, 0);
        let end = Math.min(start + range * 2, totalPages - 1);

        if (totalPages - end < range) {
            start = Math.max(totalPages - range * 2 - 1, 0);
        }

        for (let i = start; i <= end; i++) {
            pages.push(i);
        }
        return pages;
    }

    onMount(() => {
        loadArticleList(0);

        username = localStorage.getItem('username');
    });
</script>


{#await promise}
    <span class="justify-center items-center loading loading-bars loading-lg"></span>
{:then articles}
    <div class="flex max-w-7xl mx-auto">
        <div class="flex-1 p-8">
            <div class="flex space-x-4 mb-6">

                {#if username}
                    {console.log('username : ', username)}
                    <button
                            class="btn border-gray-600 btn-ghost"
                            on:click={() => {
							location.href = 'article/write';
						}}>
                        글쓰기
                    </button>
                {/if}

            </div>
            {#each pageList.articles as article}
                <div class="rounded-lg border bg-card text-card-foreground shadow-sm mb-6" data-v0-t="card">
                    <div class="flex flex-col space-y-1.5 p-6">
                        <a href="/article/{article.id}">
                            <h3 class="text-2xl font-semibold leading-none tracking-tight">
                                {article.title}
                            </h3>
                            <p class="text-sm text-muted-foreground">
                                {article.content}
                            </p>
                        </a>
                    </div>
                    <div class="p-6">
                        <div class="flex justify-between items-center">
                            <div class="btn border-gray-600 btn-ghost">카테고리</div>
                            <span>{formatDateTime(article.createdTime)}</span>
                        </div>
                    </div>
                </div>
            {:else}
                <li>검색된 결과가 없습니다.</li>
            {/each}
        </div>
    </div>
{:catch err}
    <p style="color: red;">{err.message}</p>
{/await}

<!-- 페이지네이션 컨트롤 -->
<div class="grid place-items-center mb-10">
    <div class="join">
        <button
                on:click={() => changePage(pageList.currentPage - 1)}
                disabled={pageList.currentPage <= 0}
                class="btn border-gray-600 btn-ghost mr-1"
        >
            이전
        </button>

        {#each createPageArray(pageList.currentPage, pageList.totalPages) as page}
            <input
                    on:click={() => changePage(page)}
                    class="join-item btn btn-square"
                    type="radio"
                    name="options"
                    aria-label={`Page ${page + 1}`}
                    checked
            />
        {/each}

        <button
                on:click={() => changePage(pageList.currentPage + 1)}
                disabled={pageList.currentPage >= pageList.totalPages - 1}
                class="btn border-gray-600 btn-ghost ml-1"
        >
            다음
        </button>
    </div>
</div>

<style>
    .pagination button {
        padding: 5px 10px;
        margin: 0 2px;
        border: 1px solid #ccc;
        background-color: #f8f8f8;
        cursor: pointer;
    }

    .pagination button.active {
        background-color: #007bff;
        color: white;
        border-color: #007bff;
    }

    .flex-container {
        display: flex;
        align-items: center;
    }

    .width-45 {
        width: 45%;
    }
</style>
