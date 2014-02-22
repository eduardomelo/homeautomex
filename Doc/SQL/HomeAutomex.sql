USE [HomeAutomexdb]
GO
/****** Object:  Table [dbo].[AGENDAMENTO]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AGENDAMENTO](
	[CD_AGENDAMENTO] [int] IDENTITY(1,1) NOT NULL,
	[DS_DESCRICAO] [varchar](200) NOT NULL,
	[IS_ATIVO] [bit] NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
	[CD_USUARIO] [int] NOT NULL,
	[CD_CENARIO] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_AGENDAMENTO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AMBIENTE]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AMBIENTE](
	[CD_AMBIENTE] [int] IDENTITY(1,1) NOT NULL,
	[CD_RESIDENCIA] [int] NOT NULL,
	[DS_DESCRICAO] [varchar](200) NOT NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_AMBIENTE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CENARIO]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CENARIO](
	[CD_CENARIO] [int] IDENTITY(1,1) NOT NULL,
	[DS_DESCRICAO] [varchar](200) NOT NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
	[CD_DISPOSITIVO] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_CENARIO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CENARIO_AMBIENTE]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CENARIO_AMBIENTE](
	[CD_CENARIO_AMBIENTE] [int] IDENTITY(1,1) NOT NULL,
	[CD_AMBIENTE] [int] NOT NULL,
	[CD_CENARIO] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_CENARIO_AMBIENTE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DISPOSITIVO]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DISPOSITIVO](
	[CD_DISPOSITIVO] [int] IDENTITY(1,1) NOT NULL,
	[DS_DESCRICAO] [varchar](200) NOT NULL,
	[CD_PORTA] [int] NOT NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
	[STATUS] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_DISPOSITIVO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LOG]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LOG](
	[CD_LOG] [int] IDENTITY(1,1) NOT NULL,
	[CD_USUARIO] [int] NOT NULL,
	[DESCRICAO] [varchar](200) NOT NULL,
	[DATA] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_LOG] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MODULO]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MODULO](
	[CD_MODULO] [int] IDENTITY(1,1) NOT NULL,
	[NM_IP] [varchar](50) NOT NULL,
	[NM_PORTA] [varchar](10) NOT NULL,
	[DS_NOME] [varchar](100) NOT NULL,
	[NM_MAC] [varchar](20) NULL,
	[CD_RESIDENCIA] [int] NOT NULL,
 CONSTRAINT [PK_MODULO] PRIMARY KEY CLUSTERED 
(
	[CD_MODULO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NIVEL_ACESSO]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NIVEL_ACESSO](
	[CD_NIVEL_ACESSO] [int] IDENTITY(1,1) NOT NULL,
	[DS_NOME] [varchar](200) NOT NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_NIVEL_ACESSO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PERFIL]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PERFIL](
	[CD_PERFIL] [int] NOT NULL,
	[DS_NOME] [varchar](100) NOT NULL,
	[IS_ADMIN] [bit] NOT NULL,
	[DT_CADASTRO] [datetime] NOT NULL,
	[DT_ALTERACAO] [datetime] NOT NULL,
	[DT_EXCLUSAO] [datetime] NOT NULL,
	[IS_DESATIVADO] [bit] NOT NULL,
	[CD_RESIDENCIA] [int] NOT NULL,
 CONSTRAINT [PK_PERFIL] PRIMARY KEY CLUSTERED 
(
	[CD_PERFIL] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PORTA_MODULO]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PORTA_MODULO](
	[CD_PORTA_MODULO] [int] IDENTITY(1,1) NOT NULL,
	[NM_IDENTIFICADOR] [nchar](10) NULL,
	[NM_PORTA] [varchar](5) NULL,
	[CD_TIPO] [int] NOT NULL,
	[CD_MODULO] [int] NOT NULL,
 CONSTRAINT [PK_PORTA_MODULO] PRIMARY KEY CLUSTERED 
(
	[CD_PORTA_MODULO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RESIDENCIA]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RESIDENCIA](
	[CD_RESIDENCIA] [int] IDENTITY(1,1) NOT NULL,
	[DS_LOGRADOURO] [varchar](200) NOT NULL,
	[DS_CIDADE] [varchar](50) NOT NULL,
	[DS_BAIRRO] [varchar](50) NOT NULL,
	[NM_CEP] [varchar](50) NULL,
	[NM_NUMERO] [varchar](50) NULL,
	[DS_COMPLEMENTO] [varchar](200) NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_RESIDENCIA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TIPO_PORTA_MODULO]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TIPO_PORTA_MODULO](
	[CD_TIPO_POTA_MODULO] [int] IDENTITY(1,1) NOT NULL,
	[DS_TIPO] [varchar](100) NOT NULL,
	[DS_IDENTIFICADOR] [char](1) NOT NULL,
 CONSTRAINT [PK_TIPO_PORTA_MODULO] PRIMARY KEY CLUSTERED 
(
	[CD_TIPO_POTA_MODULO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[USUARIO]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[USUARIO](
	[CD_USUARIO] [int] IDENTITY(1,1) NOT NULL,
	[DS_NOME] [varchar](200) NOT NULL,
	[DS_USUARIO] [varchar](200) NOT NULL,
	[NM_TELEFONE] [varchar](50) NULL,
	[NM_CELULAR] [varchar](50) NULL,
	[DS_EMAIL] [varchar](50) NULL,
	[DS_SENHA] [varbinary](50) NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_USUARIO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[USUARIO_RESIDENCIA]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[USUARIO_RESIDENCIA](
	[CD_USUARIO_RESIDENCIA] [int] IDENTITY(1,1) NOT NULL,
	[CD_USUARIO] [int] NOT NULL,
	[CD_RESIDENCIA] [int] NOT NULL,
	[CD_NIVEL_ACESSO] [int] NOT NULL,
 CONSTRAINT [PK_USUARIO_RESIDENCIA] PRIMARY KEY CLUSTERED 
(
	[CD_USUARIO_RESIDENCIA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UT_DISPOSITIVO]    Script Date: 21/02/2014 21:17:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UT_DISPOSITIVO](
	[CD_UT_DISPOSITIVO] [int] IDENTITY(1,1) NOT NULL,
	[ID_USUARIO] [int] NOT NULL,
	[CD_DIPOSITIVO] [int] NOT NULL,
	[DT_ULTILIZACAO] [datetime] NOT NULL,
	[STATUS] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_UT_DISPOSITIVO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[PERFIL] ADD  CONSTRAINT [DF_PERFIL_IS_DESATIVADO]  DEFAULT ((0)) FOR [IS_DESATIVADO]
GO
ALTER TABLE [dbo].[USUARIO] ADD  CONSTRAINT [DF_USUARIO_IS_DESATIVADO]  DEFAULT ((0)) FOR [IS_DESATIVADO]
GO
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
ALTER TABLE [dbo].[CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_AMBIENTE])
REFERENCES [dbo].[AMBIENTE] ([CD_AMBIENTE])
GO
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
ALTER TABLE [dbo].[DISPOSITIVO]  WITH CHECK ADD  CONSTRAINT [FK_DISPOSIVO_PORTA_MODULO] FOREIGN KEY([CD_PORTA])
REFERENCES [dbo].[PORTA_MODULO] ([CD_PORTA_MODULO])
GO
ALTER TABLE [dbo].[DISPOSITIVO] CHECK CONSTRAINT [FK_DISPOSIVO_PORTA_MODULO]
GO
ALTER TABLE [dbo].[MODULO]  WITH CHECK ADD  CONSTRAINT [FK_MODULO_RESIDENCIA] FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
ALTER TABLE [dbo].[MODULO] CHECK CONSTRAINT [FK_MODULO_RESIDENCIA]
GO
ALTER TABLE [dbo].[PERFIL]  WITH CHECK ADD  CONSTRAINT [FK_PERFIL_RESIDENCIA] FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
ALTER TABLE [dbo].[PERFIL] CHECK CONSTRAINT [FK_PERFIL_RESIDENCIA]
GO
ALTER TABLE [dbo].[PORTA_MODULO]  WITH CHECK ADD  CONSTRAINT [FK_PORTA_MODULO_MODULO] FOREIGN KEY([CD_MODULO])
REFERENCES [dbo].[MODULO] ([CD_MODULO])
GO
ALTER TABLE [dbo].[PORTA_MODULO] CHECK CONSTRAINT [FK_PORTA_MODULO_MODULO]
GO
ALTER TABLE [dbo].[PORTA_MODULO]  WITH CHECK ADD  CONSTRAINT [FK_PORTA_MODULO_TIPO_PORTA_MODULO] FOREIGN KEY([CD_TIPO])
REFERENCES [dbo].[TIPO_PORTA_MODULO] ([CD_TIPO_POTA_MODULO])
GO
ALTER TABLE [dbo].[PORTA_MODULO] CHECK CONSTRAINT [FK_PORTA_MODULO_TIPO_PORTA_MODULO]
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA]  WITH CHECK ADD FOREIGN KEY([CD_NIVEL_ACESSO])
REFERENCES [dbo].[NIVEL_ACESSO] ([CD_NIVEL_ACESSO])
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA]  WITH CHECK ADD  CONSTRAINT [FK_USUARIO_RESIDENCIA_NIVEL_ACESSO] FOREIGN KEY([CD_USUARIO_RESIDENCIA])
REFERENCES [dbo].[NIVEL_ACESSO] ([CD_NIVEL_ACESSO])
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA] CHECK CONSTRAINT [FK_USUARIO_RESIDENCIA_NIVEL_ACESSO]
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA]  WITH CHECK ADD  CONSTRAINT [FK_USUARIO_RESIDENCIA_RESIDENCIA] FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA] CHECK CONSTRAINT [FK_USUARIO_RESIDENCIA_RESIDENCIA]
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA]  WITH CHECK ADD  CONSTRAINT [FK_USUARIO_RESIDENCIA_USUARIO_RESIDENCIA] FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA] CHECK CONSTRAINT [FK_USUARIO_RESIDENCIA_USUARIO_RESIDENCIA]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'CD_NIVEL_ACESSO' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'USUARIO_RESIDENCIA', @level2type=N'CONSTRAINT',@level2name=N'FK_USUARIO_RESIDENCIA_NIVEL_ACESSO'
GO
