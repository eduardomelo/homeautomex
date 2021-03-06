USE [HomeAutomexdb]
GO
/****** Object:  Table [dbo].[CENARIO]    Script Date: 04/11/2014 20:13:08 ******/
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
PRIMARY KEY CLUSTERED 
(
	[CD_CENARIO] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RESIDENCIA]    Script Date: 04/11/2014 20:13:08 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LOG]    Script Date: 04/11/2014 20:13:08 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NIVEL_ACESSO]    Script Date: 04/11/2014 20:13:08 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[USUARIO]    Script Date: 04/11/2014 20:13:08 ******/
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
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
	[DS_SENHA] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_USUARIO] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TIPO_PORTA_MODULO]    Script Date: 04/11/2014 20:13:08 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UT_DISPOSITIVO]    Script Date: 04/11/2014 20:13:08 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[USUARIO_RESIDENCIA]    Script Date: 04/11/2014 20:13:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[USUARIO_RESIDENCIA](
	[CD_USUARIO] [int] NOT NULL,
	[CD_RESIDENCIA] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PERFIL]    Script Date: 04/11/2014 20:13:08 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AGENDAMENTO]    Script Date: 04/11/2014 20:13:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MODULO]    Script Date: 04/11/2014 20:13:08 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PORTA_MODULO]    Script Date: 04/11/2014 20:13:08 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DISPOSITIVO]    Script Date: 04/11/2014 20:13:08 ******/
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
	[FAVORITO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_DISPOSITIVO] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SENSOR]    Script Date: 04/11/2014 20:13:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[SENSOR](
	[CD_SENSOR] [int] IDENTITY(1,1) NOT NULL,
	[CD_DISPOSITIVO] [int] NULL,
	[DESCRICAO] [varchar](50) NULL,
	[VALOR] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_SENSOR] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DISPOSITIVO_CENARIO]    Script Date: 04/11/2014 20:13:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DISPOSITIVO_CENARIO](
	[ID_DISPOSITIVO_CENARIO] [int] IDENTITY(1,1) NOT NULL,
	[CD_DISPOSITIVO] [int] NOT NULL,
	[CD_CENARIO] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_DISPOSITIVO_CENARIO] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AMBIENTE]    Script Date: 04/11/2014 20:13:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AMBIENTE](
	[CD_AMBIENTE] [int] IDENTITY(1,1) NOT NULL,
	[DS_DESCRICAO] [varchar](200) NOT NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
	[CD_DISPOSITIVO] [int] NOT NULL,
	[CD_RESIDENCIA] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_AMBIENTE] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CENARIO_AMBIENTE]    Script Date: 04/11/2014 20:13:08 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Default [DF_PERFIL_IS_DESATIVADO]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[PERFIL] ADD  CONSTRAINT [DF_PERFIL_IS_DESATIVADO]  DEFAULT ((0)) FOR [IS_DESATIVADO]
GO
/****** Object:  Default [DF_USUARIO_IS_DESATIVADO]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[USUARIO] ADD  CONSTRAINT [DF_USUARIO_IS_DESATIVADO]  DEFAULT ((0)) FOR [IS_DESATIVADO]
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__35BCFE0A]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__36B12243]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__37A5467C]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__38996AB5]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__398D8EEE]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__3A81B327]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__3B75D760]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__3C69FB99]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__3D5E1FD2]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__3E52440B]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__3F466844]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__403A8C7D]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__412EB0B6]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__4222D4EF]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__4316F928]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__440B1D61]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__44FF419A]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__45F365D3]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__46E78A0C]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__47DBAE45]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__48CFD27E]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__49C3F6B7]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__4AB81AF0]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_CE__4BAC3F29]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__4CA06362]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__4D94879B]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__4E88ABD4]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__4F7CD00D]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5070F446]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5165187F]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__52593CB8]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__534D60F1]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5441852A]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5535A963]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5629CD9C]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__571DF1D5]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5812160E]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__59063A47]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__59FA5E80]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5AEE82B9]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5BE2A6F2]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5CD6CB2B]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5DCAEF64]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5EBF139D]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__5FB337D6]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__60A75C0F]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__619B8048]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AGENDAMEN__CD_US__628FA481]    Script Date: 04/11/2014 20:13:07 ******/
ALTER TABLE [dbo].[AGENDAMENTO]  WITH CHECK ADD FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
/****** Object:  ForeignKey [FK__AMBIENTE__CD_DIS__6383C8BA]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__AMBIENTE__CD_DIS__6477ECF3]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__AMBIENTE__CD_DIS__656C112C]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__AMBIENTE__CD_DIS__66603565]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__AMBIENTE__CD_DIS__6754599E]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__AMBIENTE__CD_DIS__68487DD7]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__AMBIENTE__CD_RES__693CA210]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
/****** Object:  ForeignKey [FK__AMBIENTE__CD_RES__6A30C649]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
/****** Object:  ForeignKey [FK__AMBIENTE__CD_RES__6B24EA82]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_AM__6C190EBB]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_AMBIENTE])
REFERENCES [dbo].[AMBIENTE] ([CD_AMBIENTE])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_AM__6D0D32F4]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_AMBIENTE])
REFERENCES [dbo].[AMBIENTE] ([CD_AMBIENTE])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_AM__6E01572D]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_AMBIENTE])
REFERENCES [dbo].[AMBIENTE] ([CD_AMBIENTE])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_AM__6EF57B66]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_AMBIENTE])
REFERENCES [dbo].[AMBIENTE] ([CD_AMBIENTE])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_AM__6FE99F9F]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_AMBIENTE])
REFERENCES [dbo].[AMBIENTE] ([CD_AMBIENTE])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_AM__70DDC3D8]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_AMBIENTE])
REFERENCES [dbo].[AMBIENTE] ([CD_AMBIENTE])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_CE__71D1E811]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_CE__72C60C4A]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_CE__73BA3083]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_CE__74AE54BC]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_CE__75A278F5]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__CENARIO_A__CD_CE__76969D2E]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[CENARIO_AMBIENTE]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK_DISPOSIVO_PORTA_MODULO]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO]  WITH CHECK ADD  CONSTRAINT [FK_DISPOSIVO_PORTA_MODULO] FOREIGN KEY([CD_PORTA])
REFERENCES [dbo].[PORTA_MODULO] ([CD_PORTA_MODULO])
GO
ALTER TABLE [dbo].[DISPOSITIVO] CHECK CONSTRAINT [FK_DISPOSIVO_PORTA_MODULO]
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_CE__787EE5A0]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_CE__797309D9]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_CE__7A672E12]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_CE__7B5B524B]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_CE__7C4F7684]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_CE__7D439ABD]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_CENARIO])
REFERENCES [dbo].[CENARIO] ([CD_CENARIO])
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_DI__00200768]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_DI__01142BA1]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_DI__02084FDA]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_DI__02FC7413]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_DI__7E37BEF6]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__DISPOSITI__CD_DI__7F2BE32F]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[DISPOSITIVO_CENARIO]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK_MODULO_RESIDENCIA]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[MODULO]  WITH CHECK ADD  CONSTRAINT [FK_MODULO_RESIDENCIA] FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
ALTER TABLE [dbo].[MODULO] CHECK CONSTRAINT [FK_MODULO_RESIDENCIA]
GO
/****** Object:  ForeignKey [FK_PERFIL_RESIDENCIA]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[PERFIL]  WITH CHECK ADD  CONSTRAINT [FK_PERFIL_RESIDENCIA] FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
ALTER TABLE [dbo].[PERFIL] CHECK CONSTRAINT [FK_PERFIL_RESIDENCIA]
GO
/****** Object:  ForeignKey [FK_PORTA_MODULO_MODULO]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[PORTA_MODULO]  WITH CHECK ADD  CONSTRAINT [FK_PORTA_MODULO_MODULO] FOREIGN KEY([CD_MODULO])
REFERENCES [dbo].[MODULO] ([CD_MODULO])
GO
ALTER TABLE [dbo].[PORTA_MODULO] CHECK CONSTRAINT [FK_PORTA_MODULO_MODULO]
GO
/****** Object:  ForeignKey [FK_PORTA_MODULO_TIPO_PORTA_MODULO]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[PORTA_MODULO]  WITH CHECK ADD  CONSTRAINT [FK_PORTA_MODULO_TIPO_PORTA_MODULO] FOREIGN KEY([CD_TIPO])
REFERENCES [dbo].[TIPO_PORTA_MODULO] ([CD_TIPO_POTA_MODULO])
GO
ALTER TABLE [dbo].[PORTA_MODULO] CHECK CONSTRAINT [FK_PORTA_MODULO_TIPO_PORTA_MODULO]
GO
/****** Object:  ForeignKey [FK__SENSOR__CD_DISPO__07C12930]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[SENSOR]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__SENSOR__CD_DISPO__08B54D69]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[SENSOR]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK__SENSOR__CD_DISPO__09A971A2]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[SENSOR]  WITH CHECK ADD FOREIGN KEY([CD_DISPOSITIVO])
REFERENCES [dbo].[DISPOSITIVO] ([CD_DISPOSITIVO])
GO
/****** Object:  ForeignKey [FK_USUARIO_RESIDENCIA_RESIDENCIA]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[USUARIO_RESIDENCIA]  WITH CHECK ADD  CONSTRAINT [FK_USUARIO_RESIDENCIA_RESIDENCIA] FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA] CHECK CONSTRAINT [FK_USUARIO_RESIDENCIA_RESIDENCIA]
GO
/****** Object:  ForeignKey [FK_USUARIO_RESIDENCIA_USUARIO_RESIDENCIA]    Script Date: 04/11/2014 20:13:08 ******/
ALTER TABLE [dbo].[USUARIO_RESIDENCIA]  WITH CHECK ADD  CONSTRAINT [FK_USUARIO_RESIDENCIA_USUARIO_RESIDENCIA] FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA] CHECK CONSTRAINT [FK_USUARIO_RESIDENCIA_USUARIO_RESIDENCIA]
GO
