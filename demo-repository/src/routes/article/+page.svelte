<script>
	import axios from 'axios';
	import { onMount } from 'svelte';

	let promise = Promise.resolve([]);
	let pageList = $state({
		articles: [{}],
		currentPage: 0,
		totalPages: 0,
		totalElements: 0,
		pageSize: 10
	});

	function login() {
		return new Promise(async (resolve, reject) => {
			try {
				let res = await axios.post(
					`http://localhost:8090/member/login`,
					{ username: '123', password: '123' },
					{ withCredentials: true }
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
				const res = await axios.get(`http://localhost:8090/article?page=${page}`);
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
			`http://localhost:8090/article`,
			{ title: inputTitle, content: inputContent },
			{ withCredentials: true }
		);
		console.log(res);
		console.log(inputTitle);
		console.log(inputContent);
		window.location.href = `http://localhost:5173/article/${res.data.data}`;
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
		login();
	});
</script>

{#if isPost}
	<div class="flex max-w-7xl mx-auto">
		<div class="flex-1 p-8">
			<div class="flex space-x-4 mb-6">
				<button
					class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2"
					on:click={() => {
						isPost = !isPost;
					}}
				>
					취소
				</button>
			</div>

			<div class="flex space-x-4 mb-6">
				<p>제목 입력 :</p>
				<input type="text" bind:value={inputTitle} />

				<p>내용 입력 :</p>
				<textarea bind:value={inputContent} />
			</div>

			<button
				class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2"
				on:click={() => {
					postArticle();
					isPost = !isPost;
				}}
			>
				작성 완료
			</button>
		</div>
	</div>
{:else}
	{#await promise}
		<span class="justify-center items-center loading loading-bars loading-lg"></span>
	{:then articles}
		<div class="flex max-w-7xl mx-auto">
			<div class="flex-1 p-8">
				<div class="flex space-x-4 mb-6">
					<button
						class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2"
						on:click={() => {
							isPost = !isPost;
						}}
					>
						글쓰기
					</button>
					<!-- <button
					class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2">
					category2
				</button>
				<button
					class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2">
					category3
				</button>
				<button
					class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2">
					category4
				</button>
				<button
					class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2">
					category5
				</button> -->
				</div>
				{#each pageList.articles as article}
					<div
						class="rounded-lg border bg-card text-card-foreground shadow-sm mb-6"
						data-v0-t="card"
					>
						<div class="flex flex-col space-y-1.5 p-6">
							<a href="http://localhost:5173/article/{article.id}">
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
								<div
									class="inline-flex items-center rounded-full border px-2.5 py-0.5 w-fit text-xs font-semibold transition-colors focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 border-transparent bg-secondary text-secondary-foreground hover:bg-secondary/80"
								>
									카테고리
								</div>
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
	<div>
		<button
			on:click={() => changePage(pageList.currentPage - 1)}
			disabled={pageList.currentPage <= 0}
			class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2"
		>
			이전
		</button>
		<span>페이지 {pageList.currentPage + 1} / {pageList.totalPages}</span>

		<div class="pagination">
			{#each createPageArray(pageList.currentPage, pageList.totalPages) as page}
				<button
					on:click={() => changePage(page)}
					class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2"
				>
					{`Page ${page + 1}`}
				</button>
			{/each}
		</div>
		<button
			on:click={() => changePage(pageList.currentPage + 1)}
			disabled={pageList.currentPage >= totalPages - 1}
			class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2"
		>
			다음
		</button>
	</div>
{/if}

<!-- <form th:action="@{/article}" method="get" id="searchForm">
			<input type="hidden" id="page" name="page" value={pageList.currentPage}>
		</form> -->

<!-- <script>
	import axios from 'axios';
	import '../app.css';
	import { onMount } from 'svelte';

	let promise = Promise.resolve([]);
	let pageList = $state({
		articles: [],
		currentPage: 0,
		totalPages: 0,
		pageSize: 10
	});

	function loadArticleList() {
		return new Promise(async (resolve, reject) => {
			try {
				const res = await axios.get('http://localhost:8090/article');
				console.log(res);
				pageList.articles = res.data.data.content;
				pageList.totalPages = res.data.data.totalPages;
				pageList.currentPage = res.data.data.number;
				console.log(res.data.data.content);
				resolve(pageList.articles);
				console.log(pageList.articles[0].title);
			} catch (err) {
				console.log("err");
				reject(err);
			} finally {
				console.log('done');
			}
		});
	}

	onMount(() => {
		loadArticleList();
	});
	
</script>

  {#await promise}
	<h1 style="color: royalblue;">loading...</h1>
  {:then articles}
	<ul>
	  {#each articles as article}
		<li>{article.title}</li>
	  {:else}
		<li>검색된 결과가 없습니다.</li>
	  {/each}
	</ul>
  {:catch err}
	<p style="color: red;">{err.message}</p>
  {/await} -->

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
</style>
