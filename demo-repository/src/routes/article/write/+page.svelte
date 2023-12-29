<script>
	import axios from 'axios';

	let imageFile;
	let inputTitle = $state();
	let inputContent = $state();


	function handleFileChange(event) {
    	imageFile = event.target.files[0];
  }

	async function postArticle() {
		const formData = new FormData();

		const articleData = JSON.stringify({
			title: inputTitle,
			content: inputContent
		});

		formData.append('articleRequestDto', new Blob([articleData], {type: "application/json"}));
		formData.append('imageFile', imageFile);

		const res = await axios.post(`http://localhost:8090/article`, formData, {
			headers: {
				'Content-Type': 'multipart/form-data'
			},
			withCredentials: true
		});

		console.log(res);
		console.log(inputTitle);
		console.log(inputContent);
		window.location.href = `http://localhost:5173/article/${res.data.data}`;
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

	$effect(() => {
		login();
	})

</script>

<div class="width-45 mr-auto ml-auto">
	<textarea
		class="textarea textarea-ghost text-4xl w-full"
		placeholder="제목을 입력해주세요."
		bind:value={inputTitle}
	/>
	<div class="flex items-center space-x-2 mt-6" />
	<div class="divider divider-Neutral mb-1" />

	<input type="file" class="file-input file-input-bordered file-input-primary w-full max-w-xs" on:change={handleFileChange} />

	<div class="mt-8">
		<textarea
			class="textarea textarea-ghost mt-1 w-full h-screen"
			placeholder="내용을 입력해주세요."
			bind:value={inputContent}
		/>
	</div>

	<div class="flex space-x-4 mb-6 container flex-wrap flex-row-reverse">
		<div class="flex-wrap">
			<button
				class="btn border-gray-600 btn-ghost"
				on:click={() => {
					postArticle();
					inputTitle = '';
					inputContent = '';
				}}
			>
				작성 완료
			</button>
			<button
				class="btn border-gray-600 btn-ghost"
				on:click={() => {
					inputTitle = '';
					inputContent = '';
				}}
			>
				취소
			</button>
		</div>
	</div>
</div>

<style>
	.flex-container {
		display: flex;
		align-items: center; /* 요소들을 수직 중앙에 정렬 */
	}
	.width-45 {
		width: 45%;
	}
</style>
