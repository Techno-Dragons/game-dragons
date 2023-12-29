<script>
	import axios from 'axios';
	import '../../../app.css';
	import { onMount } from 'svelte';
	import { page } from '$app/stores';
	import { stringify } from 'postcss';
	import Icon from '@iconify/svelte';

	let promise = Promise.resolve([]);
	let id = $state();
	let commentContent = $state();
	let modifyCommentContent = $state();
	let isModify = $state(false);
	let isCommentModify = $state([{}]);
	let isArticleAuthor = $state(false);
	let imageUrl = $state();

	let modifyForm = $state({
		title: '',
		content: ''
	});

	let article = $state({
		authorId: 0,
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
				const res = await axios.get(`http://localhost:8090/article/${id}`);
				console.log(res);
				article.title = res.data.data.article.title;
				article.content = res.data.data.article.content;
				article.authorId = res.data.data.article.author.id;
				article.authorname = res.data.data.article.author.nickname;
				article.createdTime = formatDateTime(res.data.data.article.createdTime);
				article.modifiedTime = formatDateTime(res.data.data.article.modifiedTime);
				article.image = res.data.data.article.image;
				article.comments = await res.data.data.article.commentList;
				resolve(article);

				for (let i = 0; i < article.comments.length; i++) {
					isCommentModify.push({
						[article.comments[i].id]: false
					});
				}

				// resolve(article);
			} catch (err) {
				console.log('err');
				reject(err);
			} finally {
				console.log('done');
			}
		});
	}

	async function loadImage() {
		try {
			const res = await axios.get(`http://localhost:8090/article/image?articleId=${id}`, {
				responseType: 'blob'
			});
			imageUrl = URL.createObjectURL(res.data);
		} catch (error) {
			// console.error('이미지를 불러오는데 실패했습니다.', error);
		}
	}

	function modifyArticle() {
		return new Promise(async (resolve, reject) => {
			try {
				console.log(modifyForm);
				let res = await axios.put(`http://localhost:8090/article/${id}`, modifyForm, {
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
					{
						username: '123',
						password: '123'
					},
					{
						withCredentials: true
					}
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
					`http://localhost:8090/article/${id}/comment`,
					{
						content: commentContent
					},
					{
						withCredentials: true
					}
				);
				console.log(res);
				//article.comments.push(commentContent);
				console.log(commentContent);
				commentContent = '';
				window.location.href = `http://localhost:5173/article/${id}`;
			} catch (error) {
				reject(error);
			} finally {
				console.log('done');
			}
		});
	}

	$effect(() => {
		id = $page.params['id'];
		promise = loadArticle();
		login();
		loadImage();
	});
	// onMount(async() => {
	// 	articleId = $page.params['articleId'];
	// 	loadArticle();
	// 	//로그인에 성공했다 나오는데 로그인이 안돼는 문제점이 있음 왜지?
	// 	login();
	// });
</script>

{#if isModify}
	<div class="width-45 mr-auto ml-auto">
		<textarea
			class="textarea textarea-ghost text-4xl w-full"
			placeholder="제목을 입력해주세요."
			bind:value={modifyForm.title}
		/>
		<div class="flex items-center space-x-2 mt-6" />
		<div class="divider divider-Neutral mb-1" />

		<div class="mt-8">
			<textarea
				class="textarea textarea-ghost mt-1 w-full h-screen"
				placeholder="내용을 입력해주세요."
				bind:value={modifyForm.content}
			/>
		</div>

		<div class="flex space-x-4 mb-6 container flex-wrap flex-row-reverse">
			<div class="flex-wrap">
				<button
					class="btn border-gray-600 btn-ghost"
					on:click={() => {
						modifyArticle();
						modifyForm.title = '';
						modifyForm.content = '';
						isModify = !isModify;
						window.location.href = `http://localhost:5173/article/${id}`;
					}}
				>
					수정 완료
				</button>
				<button
					class="btn border-gray-600 btn-ghost"
					on:click={() => {
						modifyForm.title = '';
						modifyForm.content = '';
						isModify = !isModify;
					}}
				>
					취소
				</button>
			</div>
		</div>
	</div>

	<button on:click={() => (isModify = !isModify)}>수정 취소</button>
{:else}
	{#await promise}
		<span class="loading loading-bars loading-lg"></span>
	{:then ard}
		<div class="width-45 mr-auto ml-auto">
			<h1 class="text-4xl font-bold">{article.title}</h1>
			<div class="flex items-center space-x-2 mt-6">
				<div class="flex-container">
					<div>
						<p class="text-sm font-semibold">{article.authorname}</p>
						<p class="text-sm text-gray-500">발행 : {article.createdTime}</p>
						<p class="text-sm text-gray-500 mt-2">수정 : {article.modifiedTime}</p>
					</div>
					<div class="flex space-x-4 mb-6 container flex-wrap flex-row-reverse">
						<div class="flex-wrap">
							<button
								class="btn border-gray-600 btn-ghost"
								style="color: red;"
								on:click={() => {
									axios.delete(`http://localhost:8090/article/${id}`, { withCredentials: true });
									window.location.href = `http://localhost:5173/article`;
								}}>게시글 삭제</button
							>
							<button
								class="btn border-gray-600 btn-ghost"
								on:click={() => {
									modifyForm.title = article.title;
									modifyForm.content = article.content;
									isModify = !isModify;
								}}>게시글 수정</button
							>
						</div>
					</div>
				</div>
			</div>
			<div class="divider divider-Neutral mb-1" />

			<div class="flex-container">
				<Icon class="ml-4" icon="ant-design:comment-outlined" color="white" width="20" />
				<p class="ml-2 font-sans">{article.comments.length}</p>
			</div>
			<!-- <img
					src="/placeholder.svg"
					alt="Eat Sleep Code Repeat"
					class="w-full h-auto"
					width="800"
					height="400"
					style="aspect-ratio: 800 / 400; object-fit: cover;"
				  /> -->
			<div class="divider divider-Neutral mt-1" />
			{#if imageUrl}
				<img src={imageUrl} alt="이미지" />
			{/if}

			<div class="mt-8">
				<p class="mt-4">
					{article.content}
				</p>
			</div>
			<div class="divider divider-Neutral mb-1 mt-10" />
		</div>

		<div class="grid place-items-center">
			{#each article.comments as comment}
				<div class="width-45">
					<!-- {#if article.authorId === comment.author.id} -->
					{#if comment.author}
						{console.log(comment.author.id)}

						<div class="chat chat-end">
							<div class="chat-header">
								이름!
								<time class="text-xs opacity-50">{formatDateTime(comment.createdTime)}</time>
							</div>
							<div class="chat-bubble chat-bubble-primary">{comment.content}</div>

							{#if isCommentModify[comment.id]}
								<textarea
									class="textarea textarea-primary"
									placeholder="댓글을 입력해주세요."
									bind:value={modifyCommentContent}
								></textarea>
								<button
									on:click={() => {
										if (!modifyCommentContent.trim()) {
											return;
										}
										axios.put(
											`http://localhost:8090/article/${id}/comment/${comment.id}`,
											{ content: modifyCommentContent },
											{ withCredentials: true }
										);
										modifyCommentContent = '';
										isCommentModify[comment.id] = !isCommentModify[comment.id];
										window.location.href = `http://localhost:5173/article/${id}`;
									}}>수정 완료</button
								>
								<button
									on:click={() => (isCommentModify[comment.id] = !isCommentModify[comment.id])}
									>취소</button
								>
							{:else}
								<button
									on:click={() => {
										modifyCommentContent = commentContent;
										isCommentModify[comment.id] = !isCommentModify[comment.id];
									}}>수정</button
								>
								<button
									on:click={() => {
										axios.delete(`http://localhost:8090/article/${id}/comment/${comment.id}`, {
											withCredentials: true
										});
										window.location.href = `http://localhost:5173/article/${id}`;
									}}
									style="color: red;">댓글 삭제</button
								>
							{/if}
						</div>
					{:else}
						<div class="chat chat-start">
							<div class="chat-header">
								이름!
								<time class="text-xs opacity-50">{formatDateTime(comment.createdTime)}</time>
							</div>
							<div class="chat-bubble chat-bubble-primary">{comment.content}</div>
							{#if isCommentModify[comment.id]}
								<textarea
									class="textarea textarea-primary"
									placeholder="댓글을 입력해주세요."
									bind:value={modifyCommentContent}
								></textarea>
								<button
									on:click={() => {
										if (!modifyCommentContent.trim()) {
											return;
										}
										axios.put(
											`http://localhost:8090/article/${id}/comment/${comment.id}`,
											{ content: modifyCommentContent },
											{ withCredentials: true }
										);
										modifyCommentContent = '';
										isCommentModify[comment.id] = !isCommentModify[comment.id];
										window.location.href = `http://localhost:5173/article/${id}`;
									}}>수정 완료</button
								>
								<button
									on:click={() => (isCommentModify[comment.id] = !isCommentModify[comment.id])}
									>취소</button
								>
							{:else}
								<button
									on:click={() => (isCommentModify[comment.id] = !isCommentModify[comment.id])}
									>수정</button
								>
								<button
									on:click={() => {
										axios.delete(`http://localhost:8090/article/${id}/comment/${comment.id}`, {
											withCredentials: true
										});
										window.location.href = `http://localhost:5173/article/${id}`;
									}}
									style="color: red;">댓글 삭제</button
								>
							{/if}
						</div>
					{/if}
				</div>
			{/each}

			<div class="flex-container">
				<textarea
					class="textarea textarea-primary row-span-auto"
					placeholder="댓글을 입력해주세요."
					bind:value={commentContent}
					on:keydown={(e) => {
						e.key === 'Enter' ? postComment() : undefined;
					}}
				/>
				<button class="btn border-gray-600 btn-ghost ml-1" on:click={postComment}>등록</button>
			</div>
			<input
				type="button"
				class="btn border-gray-600 btn-ghost ml-1 mt-10"
				value="뒤로가기"
				on:click={() => history.back()}
			/>
		</div>
	{/await}
{/if}

<style>
	.flex-container {
		display: flex;
		align-items: center; /* 요소들을 수직 중앙에 정렬 */
	}
	.width-45 {
		width: 45%;
	}
</style>
