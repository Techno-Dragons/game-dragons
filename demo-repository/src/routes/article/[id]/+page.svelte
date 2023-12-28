<script>
	import axios from 'axios';
	import { onMount } from 'svelte';
	import { page } from '$app/stores';

	let promise = Promise.resolve([]);
	let articleId = $state();
	let commentContent = $state();
	let isModify = $state(false);
	let isCommentModify = $state([{}]);

	let modifyForm = $state({
		title: '',
		content: ''
	});

	let article = $state({
		authorname: '',
		title: '',
		content: '',
		createdTime: '',
		modifiedTime: '',
		image: '',
		comments: [{}]
	});

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

	function loadArticle() {
		return new Promise(async (resolve, reject) => {
			try {
				const res = await axios.get(`http://localhost:8090/article/${articleId}`);
				console.log(res);
				article.title = res.data.data.article.title;
				article.content = res.data.data.article.content;
				console.log(res.data.data.article.author.kickname);
				article.authorname = res.data.data.article.author.nickname;
				article.createdTime = formatDateTime(res.data.data.article.createdTime);
				article.modifiedTime = formatDateTime(res.data.data.article.modifiedTime);
				article.image = res.data.data.article.image;
				article.comments = res.data.data.article.commentList;

				for (let i = 0; i < article.comments.length; i++) {
					isCommentModify.push({ [article.comments[i].id]: false });
				}

				resolve(article);
			} catch (err) {
				console.log('err');
				reject(err);
			} finally {
				console.log('done');
			}
		});
	}

	function modifyArticle() {
		return new Promise(async (resolve, reject) => {
			try {
				let res = await axios.put(`http://localhost:8090/article/${articleId}`, modifyForm, {
					withCredentials: true
				});
				console.log(res);
			} catch (error) {
				console.log(error);
			} finally {
				console.log('done modifyArticle');
			}
		});
	}

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

	function postComment() {
		return new Promise(async (resolve, reject) => {
			try {
				let res = await axios.post(
					`http://localhost:8090/article/${articleId}/comment`,
					{ content: commentContent },
					{ withCredentials: true }
				);
				console.log(res);
				article.comments.push(commentContent);
				console.log(commentContent);
				commentContent = '';
			} catch (error) {
				reject(error);
			} finally {
				console.log('done');
			}
		});
	}

	onMount(() => {
		articleId = $page.params['articleId'];
		loadArticle();
		//로그인에 성공했다 나오는데 로그인이 안돼는 문제점이 있음 왜지?
		login();
	});
</script>

{#if isModify}
	<div>
		title:
		<input type="text" bind:value={modifyForm.title} />
	</div>
	<div>
		content:
		<input type="text" bind:value={modifyForm.content} />
	</div>

	<button
		on:click={() => {
			if (!modifyForm.title || !modifyForm.content) {
				return;
			}
			modifyArticle();
			isModify = !isModify;
			window.location.href = `http://localhost:5173/article/${articleId}`;
		}}
	>
		수정 완료</button
	>
{:else}
	{#await promise}
		<h1 style="color: royalblue;">loading...</h1>
	{:then ar}
		<div class="article">
			<div class="article-header">
				<h1>{article.title}</h1>
				<!-- <h2>{article.subtitle}</h2> -->
				<p>{article.authorname} - {article.createdTime} - {article.modifiedTime}</p>
			</div>
			<!-- <img class="article-image" src={article.imageUrl} alt="Article image"> -->
			<div class="article-body">
				<p>{article.content}</p>
			</div>
			<div class="article-footer">
				<span>Comments: {article.comments.length}</span>
			</div>

			<button on:click={() => (isModify = !isModify)}>게시글 수정</button>
			<button
				style="color: red;"
				on:click={() => {
					axios.delete(`http://localhost:8090/article/${articleId}`, { withCredentials: true });
					window.location.href = `http://localhost:5173/article`;
				}}>게시글 삭제</button
			>

			{#each article.comments as comment (comment.id)}
				<div>
					<span style="color: royalblue;">{comment.content}</span>
					{#if isCommentModify[comment.id]}
						<textarea bind:value={commentContent}></textarea>
						<button
							on:click={() => {
								if (!commentContent.trim()) {
									return;
								}
								axios.put(
									`http://localhost:8090/article/${articleId}/comment/${comment.id}`,
									{ content: commentContent },
									{ withCredentials: true }
								);
								isCommentModify[comment.id] = !isCommentModify[comment.id];
							}}>수정 완료</button
						>
						<button on:click={() => (isCommentModify[comment.id] = !isCommentModify[comment.id])}
							>취소</button
						>
					{:else}
						<button on:click={() => (isCommentModify[comment.id] = !isCommentModify[comment.id])}
							>수정</button
						>
						<button
							on:click={() => {
								axios.delete(`http://localhost:8090/article/${articleId}/comment/${comment.id}`, {
									withCredentials: true
								});
								window.location.href = `http://localhost:5173/article/${articleId}`;
							}}
							style="color: red;">댓글 삭제</button
						>
					{/if}
				</div>
			{/each}

			<div>
				댓글 입력 :
				<textarea
					bind:value={commentContent}
					on:keydown={(e) => {
						e.key === 'Enter' ? postComment() : undefined;
					}}
				/>

				<button on:click={postComment}>등록</button>
			</div>
		</div>
	{:catch err}
		<h1 style="color: red;">{err.message}</h1>
	{/await}
{/if}

<style>
	.article {
		max-width: 600px;
		margin: auto;
		font-family: 'Arial', sans-serif;
	}

	.article-header {
		text-align: center;
	}

	.article-image {
		width: 100%;
		margin-top: 1em;
	}

	.article-body {
		margin-top: 1em;
	}

	.article-body p {
		text-align: justify;
		line-height: 1.6;
	}

	.article-footer {
		display: flex;
		justify-content: space-between;
		font-size: 0.85em;
		color: #777;
		margin-top: 1em;
	}
</style>
